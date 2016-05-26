package com.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.pojo.User;
import com.app.service.IUserService;

@Controller  
@RequestMapping("/user") 
public class UserController {
	@Resource  
    private IUserService userService;  
	
	
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
    	String username = request.getParameter("userName");
    	String password = request.getParameter("password");
    	int count = 0; 
    	count = this.userService.Login(username,password);
    	if(count>0)
    		return "homePage";
    	return "login";
    }
    
    
    @RequestMapping("/register")
    public String register(HttpServletRequest request,Model model){
    	User user = new User();
    	user.setUserName(request.getParameter("userName"));
    	user.setPassword(request.getParameter("password"));
    	user.setAge(Integer.parseInt(request.getParameter("age")));
    	user.setAddress(request.getParameter("address"));
    	if(this.userService.insert(user) == 0){
    		request.setAttribute("message", "注册失败");
    		return "register";
    	}
    	return "login";
    }
    
    public @ResponseBody String checkUsername(HttpServletRequest request,Model model) {
    	
    	return "ok";
    	
	}
    
}
