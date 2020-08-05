package com.sbp.app.util;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.sbp.app.entity.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
//@Scope("singleton") - 单例模式（默认）
//@Scope("prototype") - 多例模式
public class RedisUtil {
	
	public static final int DEFUAULT_LOGIN_TIMEOUT = 1000 * 60 * 60 * 30;// 30小时
	public static final String DEFAULT_REDIS_LOGIN_KEY = "USER_LOGIN_TABLE";// 30小时
	
	@Autowired
	@Qualifier("jedis.pool") // 根据名称注入 如Bean(name="jedis.pool.config")指定名称@Qualifier("jedis.pool.config")
	private JedisPool jp;
	
	@Value("${jedis.pool.password}")
	private String password;
	
	private Jedis getJedis() {
		Jedis j = jp.getResource();
		j.auth(password);
		return j;
	}
	private void close(Jedis j) {
		j.close();
	}
	
	@Autowired
	private RedisTemplate<String, String> rs;
	
	/**
	 * @return key对应value 或者 null
	 */
	public String get(String key) {
		Jedis j = null;
		try {
			j = getJedis();
			return j.get(key);
		}finally {
			close(j);
		}
	}
	
	/**
	 * 设置新值，将旧值返回
	 * @return 旧值
	 */
	public String getAndSet(String key,String value) {
		Jedis j = null;
		try {
			j = getJedis();
			return j.getSet(key, value);
		}finally {
			close(j);
		}
	}
	
	public String set(String key,String value) {
		Jedis j = null;
		try {
			j = getJedis();
			return j.set(key, value);
		}finally {
			close(j);
		}
	}
	
	public String setEx(String key,String value, Integer timeout) {
		Jedis j = null;
		try {
			j = getJedis();
			return j.setex(key, timeout, value);
		}finally {
			close(j);
		}
	}
	
	public void setHashObject(String key,String hashKey,Object object){
		Jedis j = null;
		String jsonStr = null;
		HashMap<String, String> map = null;
		try {
			j = getJedis();
			jsonStr = JSONObject.toJSONString(object);
			map = new HashMap<String, String>();
			map.put(hashKey, jsonStr);
			j.hmset(key, map);
		}finally {
			close(j);
		}
	}
	
	/**
	 * @param key 删除key
	 * @return true成功/false失败
	 */
	public boolean del(String key) {
		Jedis j = null;
		try {
			j = getJedis();
			if(j.del(key) == 1) {
				return true;
			}
			return false;
		}finally {
			close(j);
		}
	}
	
	/**
	 * 重新设定key的存活时间
	 * @return true成功/false失败
	 */
	public boolean expire(String key, int seconds) {
		Jedis j = null;
		try {
			j = getJedis();
			if(j.expire(key, seconds) == 1) {
				return true;
			}
			return false;
		}finally {
			close(j);
		}
	}

	public <T> T getHashObject(String key,String hashKey,Class<T> clazz) {
		Jedis j = null;
		try {
			j = getJedis();
			return (T)JSONObject.parseObject(
						j.hmget(key, hashKey).get(0), clazz
					);
		}finally {
			close(j);
		}
	}
	
	public <T> T getStringObject(String key,Class<T> clazz) {
		Jedis j = null;
		try {
			j = getJedis();
			return (T)JSONObject.parseObject(j.get(key), clazz);
		}finally {
			close(j);
		}
	}
	
}
