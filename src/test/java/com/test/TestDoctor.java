package com.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.app.commonTool.PageInfo;
import com.app.commonTool.PaginationContext;
import com.app.pojo.Doctor;
import com.app.service.IDoctorService;
import com.github.pagehelper.PageHelper;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestDoctor {
	
	@Resource
	private IDoctorService doctorService = null;
	
	@Test
	public void test1(){
		Doctor doctor = new Doctor();
		doctor = doctorService.getDoctorById(13);
		System.out.println(doctor);
		System.out.println(JSON.toJSONString(doctor));
	}
	
	@Test
	public void test2(){
		List<Doctor> doctors = new ArrayList<>();
		doctors = doctorService.getDoctorList();
		System.out.println(doctors.size());
	}
	
	@Test
	public void test3(){
		PageHelper.startPage(1, 20); // 核心分页代码  
		List<Doctor> doctors = new ArrayList<>();
		doctors = doctorService.getDoctorList();
		PageInfo<Doctor> pageInfo = new PageInfo<>(doctors);
        for (Doctor column : doctors) {  
            System.out.println(column.getName());  
        }  
	}
	
	public void displayDoctorList(){
		List<Doctor> doctors = new ArrayList<>();
		if(PaginationContext.getPageNum() == 0){
			PaginationContext.setPageNum(1);
			PaginationContext.setPageSize(15);
		}
		PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
		doctors = doctorService.getDoctorList();
		PageInfo<Doctor> pageInfo = new PageInfo<>(doctors);
		for(Doctor doctor : pageInfo.getList()){
			System.out.println(JSON.toJSONString(doctor));
		}
	}
	
	@Test
	public void test4(){
		PaginationContext.setPageNum(2);
		displayDoctorList();
	}
}
