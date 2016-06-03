package com.app.controller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
	
	@RequestMapping("/exportDoctorList")
	public void exportDoctorList(HttpServletRequest request,HttpServletResponse response, Model model){
		System.out.println("start");
		System.out.println(request.getParameter("name"));
		List<Doctor> doctors = new ArrayList<>();
		doctors = doctorService.getDoctorList();
		
		try {
			//excel基本设定
			HSSFWorkbook wb = new HSSFWorkbook();  //--->创建了一个excel文件  
	        HSSFSheet sheet = wb.createSheet("医生信息表");   //--->创建了一个工作簿  
	        HSSFRow row = sheet.createRow((int) 0);
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	        sheet.autoSizeColumn(1); 
	        HSSFCell cell;
	        String[] title = new String[]{"姓名","手机","医院"};
	        for(int i = 0;i < title.length;i++){
	        	cell = row.createCell(i);
	        	cell.setCellValue(title[i]);
	        	cell.setCellStyle(style);
	        }
	        for (int k = 0; k < doctors.size(); k++){
	        	row = sheet.createRow(k+1);
	        	Doctor list = (Doctor) doctors.get(k);
	        	row.createCell(0).setCellValue((String)list.getName());
	        	row.createCell(1).setCellValue((String)list.getMobile());
	        	row.createCell(2).setCellValue((String)list.getHospital());
	        	
	        }
	        
	        Date dt = new Date();   
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        	StringBuffer filename = new StringBuffer("医生信息表").append(sdf.format(dt));
        	String downloadFileName;
			downloadFileName = new String(filename.toString().getBytes("gb2312"), "iso8859-1");
        	
        	
        	// 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ downloadFileName+".xls");
            ServletOutputStream os = response.getOutputStream();
        	
        	wb.write(os);
        	os.flush();
        	os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
