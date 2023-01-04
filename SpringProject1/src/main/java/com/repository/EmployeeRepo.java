package com.repository;

import java.util.List;

import com.bean.Employee;

public interface EmployeeRepo {
	void info();
	String insert(Employee emp);
	Employee search(String empID);
	List<Employee> selectAll();
	List<Employee> selectBySalaryRange(int salaryMin, int salaryMax);
	String update(Employee emp);
	String delete(String empID);
	
}
