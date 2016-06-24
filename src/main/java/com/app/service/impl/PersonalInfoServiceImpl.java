package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.PersonalInfoMapper;
import com.app.pojo.PersonalInfo;
import com.app.service.IPersonalInfoService;

@Service("personalInfoService")
public class PersonalInfoServiceImpl implements IPersonalInfoService{

	@Resource
	PersonalInfoMapper personalInfoDao;
	
	@Override
	public int insertAtFirst(PersonalInfo personalInfo) {
		return personalInfoDao.insertAtFirst(personalInfo);
	}

	@Override
	public int checkUsername(String username) {
		return personalInfoDao.checkUsername(username);
	}

	@Override
	public int updatePersonalInfo(PersonalInfo personalInfo) {
		return personalInfoDao.updatePersonalInfo(personalInfo);
	}

	@Override
	public int uploadImage(String hashImage, String image, String username) {
		return personalInfoDao.uploadImage(hashImage, image, username);
	}

	@Override
	public PersonalInfo personalCenter(String username) {
		return personalInfoDao.personalCenter(username);
	}

}
