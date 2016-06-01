package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.pojo.Doctor;
import com.app.service.IDoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorListController {
	
	@Resource
	private IDoctorService doctorService;

	@RequestMapping("/doctorList")
	public String getDoctorList(HttpServletRequest request, Model model) {
		List<Doctor> doctors = new ArrayList<>();
		doctors = doctorService.getDoctorList();
		System.out.println(doctors.size());
		request.setAttribute("doctorList", doctors);
		return "doctorList";
	}
}
