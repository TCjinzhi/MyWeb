package com.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.app.pojo.Doctor;
import com.app.service.IDoctorService;

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
}
