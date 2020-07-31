package com.sbp.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Test01 {
	@Autowired
	private DataSource dataSource;
	@Test
	public void test1() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}
}
