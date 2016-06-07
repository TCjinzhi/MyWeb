package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	@RequestMapping("/wechatRoom")
	public String wechatRoom(HttpServletRequest request,Model model){
		if(request.getSession().getAttribute("username") == null)
			return "redirect:/user/index";
		return "wechatRoom";
	}
}
