package com.app.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class UseSpringDataRedis {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:redis-context.xml");
		// 获取Spring提供的RedisTemplate类此类封装了Jedis，简化操作
	    RedisTemplate<String, List<String>> redisTemplate = context.getBean("jedisTemplate", RedisTemplate.class);
	    // Spring 提供的各种Redis结构的key-value操作类
	    ValueOperations<String, List<String>> value = redisTemplate.opsForValue();
//	    HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
//	    ListOperations<String, List<String>> list = redisTemplate.opsForList();
//	    HyperLogLogOperations<String, List<String>> hyperLogLog = redisTemplate.opsForHyperLogLog();
//	    SetOperations<String, List<String>> set = redisTemplate.opsForSet();
//	    ZSetOperations<String, List<String>> zSet = redisTemplate.opsForZSet();
	    
	    
	    
	    List<String> listValue = new ArrayList<String>();
	    listValue.add("001");
	    listValue.add("002");
	    value.set("list", listValue);
	    System.out.println(value.get("list"));
	    
	    // 关闭Spring容器释放资源
	    context.close();
	}
}
