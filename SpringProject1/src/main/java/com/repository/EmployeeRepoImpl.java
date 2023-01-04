package com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.bean.Employee;
import com.bean.EmployeeMapper;
import com.connection.GetConnection;

@Component
public class EmployeeRepoImpl implements EmployeeRepo {

	@Autowired
	GetConnection con;

	private JdbcTemplate jdbcTemplate;

	public void info() {
		System.out.println("connection Object->" + con.getTemplateObject());
		jdbcTemplate = con.getTemplateObject();
	}

	private final String SQL_FIND_EMPLOYEE = "select * from employee where empid = ?";
	private final String SQL_FIND_ALL_EMPLOYEE = "select * from employee";
	private final String SQL_DELETE_EMPLOYEE = "delete from employee where empid = ?";
	private final String SQL_UPDATE_EMPLOYEE = "update employee set empfname = ?,emplname = ?, empsalary =?, empaddr = ? where empid = ?";
	private final String SQL_INSERT_EMPLOYEE = "insert into employee(empid, empfname, emplname, empsalary, empaddr) values(?,?,?,?,?)";
	private final String SQL_SEARCH_BY_SALARY_RANGE = "select * from employee where empsalary between ? and ?";

	@Override
	public String insert(Employee emp) {
		String status = "";
		System.out.println(emp);
		try {
			int count = jdbcTemplate.update(SQL_INSERT_EMPLOYEE, emp.getEmpID(), emp.getEmpFName(), emp.getEmpLName(),
					emp.getEmpSalary(), emp.getEmpAddr());
			if (count == 1)
				status = "Employee Information Added Successfully";
			else
				status = "Insertion Failed !!!";
		} catch (DataIntegrityViolationException ex) {
			status = "Employee ID already exists! Please try again!";
		}
		return status;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Employee search(String empid) {
		return jdbcTemplate.queryForObject(SQL_FIND_EMPLOYEE, new Object[] { empid }, new EmployeeMapper());
	}

	@Override
	public List<Employee> selectAll() {
		return jdbcTemplate.query(SQL_FIND_ALL_EMPLOYEE, new EmployeeMapper());
	}

	@Override
	public List<Employee> selectBySalaryRange(int salaryMin, int salaryMax) {
		return jdbcTemplate.query(SQL_SEARCH_BY_SALARY_RANGE, new EmployeeMapper(),
				new Object[] { salaryMin, salaryMax });
	}

	@Override
	public String update(Employee emp) {
		String status = "";
		int count = jdbcTemplate.update(SQL_UPDATE_EMPLOYEE, emp.getEmpFName(), emp.getEmpLName(), emp.getEmpSalary(),
				emp.getEmpAddr(), emp.getEmpID());
		if (count == 1)
			status = "Employee Details Updated Successfully";
		else
			status = "Updation Failed !!!";
		return status;
	}

	@Override
	public String delete(String empid) {
		String status = "";
		int count = jdbcTemplate.update(SQL_DELETE_EMPLOYEE, empid);
		if (count == 1)
			status = "Employee Record Deleted successfully";
		else
			status = "Deletion Unsuccessfull !!!";
		return status;
	}

}
