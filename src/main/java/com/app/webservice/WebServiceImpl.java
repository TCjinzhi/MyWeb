package com.app.webservice;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.app.dao.DoctorMapper;
import com.app.pojo.Doctor;

@WebService
public class WebServiceImpl implements WebServiceI {
	
	@Resource
	private DoctorMapper docters;

	@Override
	public String sayHello(String username) {
		System.out.println("Say hello to" + username);
		return "Helle," + username;
	}

	@Override
	public List<Doctor> getDoctorList() {
		return docters.getDoctorList(null);
	}

}
