package com.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.bean.Student;
import com.bean.StudentMapper;
import com.connection.GetConnection;

@Component
public class StudentRepoImpl implements StudentRepo{

	@Autowired
	GetConnection con;
	
	private JdbcTemplate jdbcTemplate;
	
	
	public void info() {
		System.out.println("connection Object->"+con.getTemplateObject());
		jdbcTemplate=con.getTemplateObject();
	}

	private final String SQL_FIND_STUDENT = "select * from student where sid = ?";
	private final String SQL_DELETE_STUDENT = "delete from student where sid = ?";
	private final String SQL_UPDATE_STUDENT = "update student set sname = ?,saddr = ? where sid = ?";
	private final String SQL_GET_ALL = "select * from student";
	private final String SQL_INSERT_STUDENT = "insert into student(sid, sname, saddr) values(?,?,?)";
	
	@Override
	public String insert(Student std) {
		String status = "";
		System.out.println(std);
			int count= jdbcTemplate.update(SQL_INSERT_STUDENT,std.getSid(),std.getSname(),std.getSaddr());
			if (count == 1)
				status = "Student Information Added Successfully";
			else
				status = "Insertion Failed !!!";
		return status;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Student search(String sid) {
		return jdbcTemplate.queryForObject(SQL_FIND_STUDENT, new Object[] { sid }, new StudentMapper());
	}

	@Override
	public String update(Student std) {
		String status = "";
			int count = jdbcTemplate.update(SQL_UPDATE_STUDENT,std.getSname(),std.getSaddr(),std.getSid());
			if (count == 1)
				status = "Student Details Updated Successfully";
			else
				status = "Updation Failed !!!";
		return status;
	}

	@Override
	public String delete(String sid) {
		String status = "";
			int count = jdbcTemplate.update(SQL_DELETE_STUDENT,sid);
			if (count == 1)
				status = "Student Record Deleted successfully";
			else
				status = "Deletion Unsuccessfull !!!";
		return status;
	}
	
}
