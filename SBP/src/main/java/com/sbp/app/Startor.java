package com.sbp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * -参考文章：
 * http://c.biancheng.net/view/5316.html
 * 
 * 
 * @author weiqz
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class Startor {
	public static void main(String[] args) {
		SpringApplication.run(Startor.class, args);
	}
}
