package com.app.commonTool;

import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.request.RequestContextHolder;

import com.app.pojo.User;
import com.app.service.IUserService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

//@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class CookieUtil {
	
	//自定义的cookie name
	private final static String cookieDomainName = "Keep";
	
	//定义cookie的保存时间；暂设置为两周
	private final static long cookieAge = 60 * 60 * 24 * 7 * 2;
	
	//定义cookie的最大保存时间；暂定位一年
	private final static int cookieMaxAge = 60 * 60 * 24 * 365;
	
	//自定义的加密码
	private final static String webkey = "secret";

	/**
     * Cookie 追加
     * @return
     * @throws Exception
     */
    public static void saveCookie(User user,HttpServletResponse response){
    	
    	//cookie有效期
    	long valitime = System.currentTimeMillis()+ (cookieAge * 1000);
    	
    	//MD5加密用户信息
    	String cookieValueWithMd5 = StrUtil.getMD5Str(user.getUserName() +":"+ user.getPassword()
    													+ ":" + valitime + ":" + webkey);
    	//实际保存的cookie
    	String cookieValue = user.getUserName() + ":" + valitime + ":" + cookieValueWithMd5;
    	//再对cookie进行一次BASE64编码
    	String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes()));
    	
    	Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64);
    	
        cookie.setMaxAge(cookieMaxAge);
        
        //cookie有效路径
        cookie.setPath("/");
        
        response.addCookie(cookie);
    }
    /**
     * 读取Cookie,自动完成登陆操作
     * @return
     * @throws Exception 
     */
    public static void readCookie(HttpServletRequest request, HttpServletResponse response,
    		FilterChain chain) throws Exception{
    	
    	Cookie cookies[] = request.getCookies();
    	String cookieValue = null;
    	if(cookies!=null){
    		for(int i = 0; i < cookies.length; i++){
    			if(cookieDomainName.equals(cookies[i].getName()))
    				cookieValue = cookies[i].getValue();
    		}
    	}
    	
    	if(cookieValue == null)
    		return;
    	
    	String cookieValueAfterDecode = new String (Base64.decode(cookieValue),"utf-8");
    	String cookieValues[] = cookieValueAfterDecode.split(":");
    	if(cookieValues.length!=3){
	        response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.println("你正在用非正常方式进入本站...");
	        out.close();
	        return;
    	}
    	//判断有效期
    	long validTimeInCookie = new Long(cookieValues[1]);
    	if(validTimeInCookie < System.currentTimeMillis()){
    		clearCookie(response);
    		response.setContentType("text/html;charset=utf-8");
    		PrintWriter out = response.getWriter();
	        out.println("您保存的密码已经过期");
	        out.close();
	        return;
    	}
    	String username = cookieValues[0];
    	ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
    	IUserService userService=(IUserService) ac.getBean("userService");
    	//根据用户名到数据库中检查用户是否存在
	    User user = userService.checkUsername(username);
	    if(user!=null){
	         String md5ValueInCookie = cookieValues[2];
	         String md5ValueFromUser =StrUtil.getMD5Str(user.getUserName() + ":" + user.getPassword()
	                       + ":" + validTimeInCookie + ":" + webkey);
	         //将结果与Cookie中的MD5码相比较,如果相同,写入Session,自动登陆成功,并继续用户请求
	         if(md5ValueFromUser.equals(md5ValueInCookie)){
	                HttpSession session = request.getSession(true);
	                session.setAttribute("user", user);
	                chain.doFilter(request, response);
	         }
	    }else{
	    	//返回为空执行
	    	response.setContentType("text/html;charset=utf-8");
	    	PrintWriter out = response.getWriter();
	    	out.println("验证错误！");
	    	out.close();
	    	return;
	     }

    	
    }
    
    /**
     * 清除cookie
     * @param response
     */
    public static void clearCookie(HttpServletResponse response){
		Cookie cookie = new Cookie(cookieDomainName, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
    }
    
    
    /**
     * Cookie 追加
     * @return
     * @throws Exception
     */
    public static void addCookie(String name,String value, HttpServletResponse response){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(cookieMaxAge);
        response.addCookie(cookie);
    }
    
    /**
     * Cookie 取得
     * @return
     * @throws Exception
     */
    public static String getCookie(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies)
            {
                if(cookie.getName().equals(name))
                {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
