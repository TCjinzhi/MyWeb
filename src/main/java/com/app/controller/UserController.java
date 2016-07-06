package com.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.app.commonTool.StrUtil;
import com.app.pojo.CheckUsername;
import com.app.pojo.User;
import com.app.service.IUserService;
import com.test.TestMyBatis;

import sun.invoke.empty.Empty;

@Controller  
@RequestMapping("/user") 
public class UserController {
	@Resource  
    private IUserService userService;  
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping("/index")  
    public String toIndex(HttpServletRequest request,Model model){  
        return "login";
    }
      
    @RequestMapping("/showUser")  
    public String toShowUser(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        User user = this.userService.getUserById(userId);  
        model.addAttribute("user", user);  
        return "showUser";
    }
    
    @RequestMapping("/login")
    public String login(HttpServletRequest request,Model model){
    	String username;
    	String password;
    	if(request.getSession().getAttribute("username") != null){
    		username  = (String) request.getSession().getAttribute("username");
    		password = (String) request.getSession().getAttribute("password");
    	} else {
    		username = request.getParameter("userName");
    		password = request.getParameter("password");
    		if(password == null){
    			return "login";
    		}
    		password = StrUtil.getEncryptStr(password);
    	}
    	logger.info("username:"+ username + ",password:"+ password +" 正在尝试登陆系统！");
    	int count = 0; 
    	count = this.userService.Login(username,password);
    	if(count>0){
    		request.getSession().setAttribute("username", username);
    		request.getSession().setAttribute("password", password);
    		if(!request.getParameter("veryCode").equalsIgnoreCase(request.getSession().getAttribute("code").toString())){
    			request.setAttribute("message", "验证码错误");
    			return "login";
    		}
    		return "homePage";
    	}
    	request.setAttribute("message", "账号或密码不正确");
    	return "login";
    }
    
    
    @RequestMapping("/register")
    public String register(HttpServletRequest request,Model model){
    	User user = new User();
    	String password = request.getParameter("password");
    	password = StrUtil.getEncryptStr(password);
    	user.setUserName(request.getParameter("userName"));
    	user.setPassword(password);
    	user.setAge(Integer.parseInt(request.getParameter("age")));
    	user.setAddress(request.getParameter("address"));
    	if(this.userService.insert(user) == 0){
    		request.setAttribute("message", "注册失败");
    		return "register";
    	}
    	return "login";
    }
    
    @RequestMapping("/checkUsername")
    @ResponseBody
    public CheckUsername checkUsername(@RequestBody User user) {
    	CheckUsername cuser = new CheckUsername();
    	cuser.setUsername(user.getUserName());
    	User getuser = this.userService.checkUsername(user.getUserName());
    	if(getuser != null ){
    		cuser.setCode(1);
    		cuser.setMsg("用户名已存在！");
    	} else{
    		cuser.setCode(0);
    		cuser.setMsg("恭喜，用户名可用！");
    	}
    	
    	System.out.println(JSON.toJSONString(cuser));
    	return cuser;
	}
    
}
