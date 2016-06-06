package com.app.pojo;

import java.io.Serializable;

public class Doctor implements Serializable {
	
	//序列换的版本控制
	private static final long serialVersionUID = 1l;
	
	private Integer id;
	
	private String name;
	
	private String mobile;
	
	private String password;
	
	private Boolean gender;
	
	private String hospital;
	
	private String department;
	
	private String office;
	
	private String resume;
	
	private String level;
	
	private String province;
	
	private String city;
	
	private Integer priority;
	
	private Boolean famous_flag;
	
	private Integer integral;
	
	private Boolean get_flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getFamous_flag() {
		return famous_flag;
	}

	public void setFamous_flag(Boolean famous_flag) {
		this.famous_flag = famous_flag;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Boolean getGet_flag() {
		return get_flag;
	}

	public void setGet_flag(Boolean get_flag) {
		this.get_flag = get_flag;
	}

}
