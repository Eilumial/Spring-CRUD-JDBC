package com.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:datasource.properties")
public class GetConnection {

	@Autowired
	Environment environment;

	private String URL = "url";
	private String USERNAME = "uname";
	private String PASSWORD = "password";

	public JdbcTemplate getTemplateObject() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty(URL));
		dataSource.setUsername(environment.getProperty(USERNAME));
		dataSource.setPassword(environment.getProperty(PASSWORD));
		return new JdbcTemplate(dataSource);
	}

	public void info() {
		System.out.println("Connection Object created");
	}
}
