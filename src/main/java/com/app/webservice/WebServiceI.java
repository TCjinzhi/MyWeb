package com.app.webservice;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.app.pojo.Doctor;
import com.app.service.IDoctorService;
import com.app.service.IUserService;
import com.app.service.impl.UserServiceImpl;

@WebService
public interface WebServiceI {	
	@WebMethod
	String sayHello(String username);
	
	@WebMethod
	List<Doctor> getDoctorList();
}
