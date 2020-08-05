package com.sbp.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sbp.app.Startor;
import com.sbp.app.util.RedisUtil;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Startor.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test01 {
	@Autowired
	private RedisUtil r;
	
	@Test
	public void test1() {
		System.out.println("name=" + r.get("name"));
		r.set("name", "weiqunzhen");
		System.out.println("name=" + r.get("name"));
	}
	public static void main(String[] args) {
		System.out.println(get());
	}
	public static String get() {
		try {
			int i = gerError();
			return "return";
		}finally {
			System.out.println("finally");
		}
	}
	public static int gerError(){
		return 1/0;
	}
}