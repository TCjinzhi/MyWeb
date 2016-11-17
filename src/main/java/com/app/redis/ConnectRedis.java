package com.app.redis;

import redis.clients.jedis.Jedis;

public class ConnectRedis {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("115.159.48.53", 6379);
		System.out.println(jedis.ping());
	}
}
