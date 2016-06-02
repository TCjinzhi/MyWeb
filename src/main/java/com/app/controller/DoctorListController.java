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
		System.out.println(request.getParameter("action"));
		
		//获取分页的历史session
		@SuppressWarnings("unchecked")
		PageInfo<Doctor> pageInfoOld = (PageInfo<Doctor>) request.getSession().getAttribute("doctorList");
		/**
		 * action
		 * 前一页：prePage
		 * 后一页：nextPage
		 * 首页：firstPage
		 * 尾页：lastPage
		 */
		String action = request.getParameter("action");
		
		List<Doctor> doctors = new ArrayList<>();
		
		if(action == null || action.equals("")){
			if(PaginationContext.getPageNum() == 0)
				PaginationContext.setPageNum(1);
		}
		else{
			switch (action) {
			case "firstPage":
				PaginationContext.setPageNum(1);
				break;
			case "prePage":
				if(pageInfoOld.isHasPreviousPage())
					PaginationContext.setPageNum(pageInfoOld.getPageNum()-1);
				else
					PaginationContext.setPageNum(pageInfoOld.getPageNum());
				break;
			case "nextPage":
				if(pageInfoOld.isHasNextPage())
					PaginationContext.setPageNum(pageInfoOld.getPageNum()+1);
				else
					PaginationContext.setPageNum(pageInfoOld.getPageNum());
				break;
			case "lastPage":
				PaginationContext.setPageNum(pageInfoOld.getPages());
				break;
			default:
				if(PaginationContext.getPageNum() == 0)
					PaginationContext.setPageNum(1);
				break;
			}
		}
		
		PaginationContext.setPageSize(15);
		PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
		doctors = doctorService.getDoctorList();
		PageInfo<Doctor> pageInfo = new PageInfo<>(doctors);
		
		request.getSession().setAttribute("doctorList", pageInfo);
		return "doctorList";
	}
	
//	@RequestMapping("/nextPage")
//	public String nextPage(HttpServletRequest request, Model model){
//		@SuppressWarnings("unchecked")
//		PageInfo<Doctor> pageInfo = (PageInfo<Doctor>) request.getSession().getAttribute("doctorList");
//		System.out.println(pageInfo.isHasNextPage());
//		if(pageInfo.isHasNextPage()){
//			PaginationContext.setPageNum(pageInfo.getPageNum()+1);
//		} else{
//			PaginationContext.setPageNum(pageInfo.getPageNum());
//		}
//		getDoctorList(request, model);
//		return "doctorList";
//	}
	
	
	
}
