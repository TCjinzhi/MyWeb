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

}
