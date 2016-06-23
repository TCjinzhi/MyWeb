package com.app.pojo;

import java.io.Serializable;

public class PersonalInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String realname;
	
	private String email;
	
	private String telephone;
	
	private String qq;
	
	private String twitter;
	
	private String intro;
	
	//image路径的hash值，建立索引提升搜索速度
	private String imageHash;
	
	private String image;

	public PersonalInfo() {
		super();
	}

	public PersonalInfo(String username, String realname, String email, String telephone, String qq, String twitter,
			String intro, String image) {
		super();
		this.username = username;
		this.realname = realname;
		this.email = email;
		this.telephone = telephone;
		this.qq = qq;
		this.twitter = twitter;
		this.intro = intro;
		this.image = image;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	

	public String getImageHash() {
		return imageHash;
	}

	public void setImageHash(String imageHash) {
		this.imageHash = imageHash;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
