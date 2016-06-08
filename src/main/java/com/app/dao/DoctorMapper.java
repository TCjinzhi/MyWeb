package com.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.app.pojo.Doctor;

public interface DoctorMapper {
	Doctor selectByPrimaryKey(Integer id);
	
	List<Doctor> getDoctorList(@Param(value="name")String name);
	
}
