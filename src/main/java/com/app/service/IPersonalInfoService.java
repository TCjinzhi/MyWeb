package com.app.service;

import com.app.pojo.PersonalInfo;

public interface IPersonalInfoService {
	int insertAtFirst(PersonalInfo personalInfo);
	
	int checkUsername(String username);

	int updatePersonalInfo(PersonalInfo personalInfo);
	
	int uploadImage(String hashImage,String image,String username);
	
	PersonalInfo personalCenter(String username);
}
