package com.app.dao;

import java.util.List;

import com.app.pojo.Doctor;

public interface DoctorMapper {
	Doctor selectByPrimaryKey(Integer id);
	
	List<Doctor> getDoctorList();
	
}
