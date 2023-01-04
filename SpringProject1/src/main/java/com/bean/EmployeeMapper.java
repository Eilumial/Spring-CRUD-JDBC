package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements org.springframework.jdbc.core.RowMapper<Employee> {
	public Employee mapRow(ResultSet resultSet, int i) throws SQLException {

		Employee emp = new Employee();
		
		emp.setEmpID(resultSet.getString("empid"));
		emp.setEmpFName(resultSet.getString("empfname"));
		emp.setEmpLName(resultSet.getString("emplname"));
		emp.setEmpSalary(resultSet.getInt("empsalary"));
		emp.setEmpAddr(resultSet.getString("empaddr"));
//		person.setSid(resultSet.getString("sid"));
//		person.setSname(resultSet.getString("sname"));
//		person.setSaddr(resultSet.getString("saddr"));
		return emp;
	}
}
