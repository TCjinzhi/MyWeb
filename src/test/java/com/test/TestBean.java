package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.app.pojo.User;
import com.app.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestBean {
	
	@Test
	public void test1(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
    	
    	IUserService userService=(IUserService) ac.getBean("userService");
    	
    	User user =	userService.checkUsername("admin");
    	
    	System.out.println(JSON.toJSONString(user));
	}
	
}
