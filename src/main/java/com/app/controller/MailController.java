package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.app.commonTool.MailUtil;
import com.app.pojo.Mail;

@Controller
@RequestMapping("/mail")
public class MailController {

	@RequestMapping("/sendmail")
	public String sendmail(HttpServletRequest request,Model model){
		Mail mail =new Mail(request.getParameter("fromMail"),
							request.getParameter("user"),
							request.getParameter("password"),
							request.getParameter("toMail"),
							request.getParameter("mailTitle"),
							request.getParameter("mailContent")
				);
		System.out.println(JSON.toJSONString(mail));
		try {
			MailUtil.sendMail(mail);
			return "mailSuccess";
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return "mailError";
		}
		
	}
}
