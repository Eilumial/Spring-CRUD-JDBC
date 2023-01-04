package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bean.Employee;
import com.repository.EmployeeRepo;

@Component("empservice")
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	EmployeeRepo empDAO;

	String status = "";

	@Override
	public String addEmployee(Employee emp) {
		status = empDAO.insert(emp);
		return status;
	}

	@Override
	public Employee searchDetail(String empid) {
		Employee emp = empDAO.search(empid);
		return emp;
	}
	
	@Override
	public List<Employee> searchAll(){
		List<Employee> empList = empDAO.selectAll();
		return empList;
	}
	
	@Override
	public List<Employee> searchBySalaryRange(int salaryMin, int salaryMax){
		List<Employee> empList = empDAO.selectBySalaryRange(salaryMin, salaryMax);
		return empList;
	}
	
	@Override
	public String updateDetails(Employee emp) {
		status = empDAO.update(emp);
		return status;
	}

	@Override
	public String deleteRecord(String empid) {
		String status = empDAO.delete(empid);
		return status;
	}

	@Override
	public void info() {
		System.out.println("Employee Service Object Created");
		empDAO.info();
	}
}
