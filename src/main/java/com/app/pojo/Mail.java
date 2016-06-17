package com.app.pojo;

import java.io.Serializable;

public class Mail implements Serializable {
	
	private static final long serialVersionUID = 1l;
	
	public static final String ENCODING = "utf-8";

	 //发送邮箱
	private String fromMail;
	
	//用户名
	private String user;
	
	//密码
	private String password;
	
	//目的邮箱
	private String toMail;
	
	//邮件标题
	private String mailTitle;
	
	//邮件内容
	private String mailContent;
	
	public Mail() {
		super();
	}

	public Mail(String fromMail, String user, String password, String toMail, String mailTitle, String mailContent) {
		super();
		this.fromMail = fromMail;
		this.user = user;
		this.password = password;
		this.toMail = toMail;
		this.mailTitle = mailTitle;
		this.mailContent = mailContent;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	

}
