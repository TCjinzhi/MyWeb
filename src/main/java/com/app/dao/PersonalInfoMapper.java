package com.app.dao;

import org.apache.ibatis.annotations.Param;

import com.app.pojo.PersonalInfo;

public interface PersonalInfoMapper {
	int insertAtFirst(PersonalInfo personalInfo);
	
	int checkUsername(@Param(value="username")String username);

	int updatePersonalInfo(PersonalInfo personalInfo);
}
