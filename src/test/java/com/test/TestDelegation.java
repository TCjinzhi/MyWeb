package com.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.app.pojo.Delegation;
import com.app.service.IDelegationService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestDelegation {
	
	@Resource
	private IDelegationService delegationService;
	
	@Test
	public void test1(){
		Delegation delegation = new Delegation();
		delegation = delegationService.getDelegationById(1);
		System.out.println(delegation);
		System.out.println(JSON.toJSONStringWithDateFormat(delegation, "yyyy-MM-dd HH:mm:ss"));
	}
	
}
