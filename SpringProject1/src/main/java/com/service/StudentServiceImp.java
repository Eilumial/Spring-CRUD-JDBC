package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bean.Student;
import com.repository.StudentRepo;

@Component("stuservice")
public class StudentServiceImp implements StudentService {

	@Autowired
	StudentRepo studentdao;

	String status = "";

	@Override
	public String addStudent(Student std) {
		status = studentdao.insert(std);
		return status;
	}

	@Override
	public Student searchDetail(String sid) {
		Student std = studentdao.search(sid);
		return std;
	}

	@Override
	public String updateDetails(Student std) {
		status = studentdao.update(std);
		return status;
	}

	@Override
	public String deleteRecord(String sid) {
		String status = studentdao.delete(sid);
		return status;
	}

	@Override
	public void info() {
		System.out.println("Student Service Object Created");
		studentdao.info();
	}
}
