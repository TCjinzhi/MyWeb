package com.app.pojo;

import java.util.List;

public class APIResponse {

	private String code;
	
	private String msg;
	
	private List<APIGift> data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<APIGift> getData() {
		return data;
	}

	public void setData(List<APIGift> data) {
		this.data = data;
	}
	
	
}
