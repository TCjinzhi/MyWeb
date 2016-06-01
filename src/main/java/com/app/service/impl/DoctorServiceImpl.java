package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.DoctorMapper;
import com.app.pojo.Doctor;
import com.app.service.IDoctorService;

@Service("doctorService")
public class DoctorServiceImpl implements IDoctorService {
	
	@Resource
	private DoctorMapper doctorDao;

	@Override
	public Doctor getDoctorById(Integer id) {
		return doctorDao.selectByPrimaryKey(id);
	}

}
