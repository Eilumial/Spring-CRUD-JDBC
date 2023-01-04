package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements org.springframework.jdbc.core.RowMapper<Student> {
	public Student mapRow(ResultSet resultSet, int i) throws SQLException {

		Student person = new Student();
		person.setSid(resultSet.getString("sid"));
		person.setSname(resultSet.getString("sname"));
		person.setSaddr(resultSet.getString("saddr"));
		return person;
	}
}
