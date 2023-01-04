package com.service;

import java.util.List;

import com.bean.Employee;

public interface EmployeeService {
	void info();
	String addEmployee(Employee emp);
	Employee searchDetail(String empid);
	List<Employee> searchAll();
	List<Employee> searchBySalaryRange(int salaryMin, int salaryMax);
	String updateDetails(Employee emp);
	String deleteRecord(String empid);
	
}
