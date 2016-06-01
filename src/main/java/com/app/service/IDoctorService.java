package com.app.service;

import java.util.List;

import com.app.pojo.Doctor;

public interface IDoctorService {
	Doctor getDoctorById(Integer id);
	
	List<Doctor> getDoctorList();
}
