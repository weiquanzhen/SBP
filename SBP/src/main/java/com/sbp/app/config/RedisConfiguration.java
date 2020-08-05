package com.sbp.app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 配置redis连接池
 * @author weiqz
 */
@Configuration
public class RedisConfiguration {
	
	/**
	 * @Qualifier 根据名称注入 如Bean(name="jedis.pool.config")指定名称@Qualifier("jedis.pool.config")
	 * @Value("${*}") 去配置文件找对应配置的值
	 * @param poolConfig 连接池配置
	 * @return 连接池
	 */
	@Bean("jedis.pool")
	public JedisPool jedisPool(
			@Qualifier("jedis.pool.config")JedisPoolConfig poolConfig,
			@Value("${jedis.pool.host}")String host,
			@Value("${jedis.pool.port}")int port) {
		return new JedisPool(poolConfig, host, port);
	}
	
	@Bean(name="jedis.pool.config")
	public JedisPoolConfig jedisPoolConfig(
			@Value("${jedis.pool.config.max_total}")int maxTotal,
			@Value("${jedis.pool.config.max_idle}")int maxIdle,
			@Value("${jedis.pool.config.max_wait_millis}")int  maxWaitMillis) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWaitMillis);
		return config;
	}
}
