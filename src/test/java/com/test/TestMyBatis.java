package com.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.app.pojo.Doctor;
import com.app.pojo.User;
import com.app.service.IDoctorService;
import com.app.service.IUserService;
import com.app.service.impl.DoctorServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	// private ApplicationContext ac = null;
	@Resource
	private IUserService userService = null;
	
	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }

	@Test
	public void test1() {
		User user = userService.getUserById(8);
		 System.out.println(JSON.toJSONString(user));
		// logger.info("值："+user.getUserName());
		logger.info(JSON.toJSONString(user));
	}
	
	@Test
	public void test2(){
		int count = userService.Login("admin", "admin");
		System.out.println(count);
	}
	
	@Test
	public void test3(){
		int count = userService.checkUsername("zdsda");
		System.out.println(count);
	}
	
}
