package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.commonTool.StrUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestStrUtil {
	
	@Test
	public void test1(){
		String result =	StrUtil.getEncryptStr("test1");
		System.err.println(result);
		//f0dc70e7e86a26a7e0be2669652621b7
	}
}
