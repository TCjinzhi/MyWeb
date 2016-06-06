package com.app.pojo;

import java.io.Serializable;
public class CheckUsername implements Serializable {
	
	//序列换的版本控制
	private static final long serialVersionUID = 1l;
	
	private Integer code;
	
	private String msg;
	
	private String username;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
