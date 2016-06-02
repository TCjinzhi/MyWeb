package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.commonTool.PageInfo;
import com.app.commonTool.PaginationContext;
import com.app.pojo.Doctor;
import com.app.service.IDoctorService;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/doctor")
public class DoctorListController {
	
	@Resource
	private IDoctorService doctorService;

	@RequestMapping("/doctorList")
	public String getDoctorList(HttpServletRequest request, Model model) {
		List<Doctor> doctors = new ArrayList<>();
		if(PaginationContext.getPageNum() == 0){
			PaginationContext.setPageNum(1);
		}
		PaginationContext.setPageSize(15);
		PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
		doctors = doctorService.getDoctorList();
		PageInfo<Doctor> pageInfo = new PageInfo<>(doctors);
		
		request.getSession().setAttribute("doctorList", pageInfo);
		return "doctorList";
	}
	
	@RequestMapping("/nextPage")
	public String nextPage(HttpServletRequest request, Model model){
		@SuppressWarnings("unchecked")
		PageInfo<Doctor> pageInfo = (PageInfo<Doctor>) request.getSession().getAttribute("doctorList");
		System.out.println(pageInfo.isHasNextPage());
		if(pageInfo.isHasNextPage()){
			PaginationContext.setPageNum(pageInfo.getPageNum()+1);
		} else{
			PaginationContext.setPageNum(pageInfo.getPageNum());
		}
		getDoctorList(request, model);
		return "doctorList";
	}
	
}
