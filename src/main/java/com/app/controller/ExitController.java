package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/setting") 
public class ExitController {
	
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/user/index";
	}
}
