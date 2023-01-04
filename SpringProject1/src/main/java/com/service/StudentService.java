package com.service;

import com.bean.Student;

public interface StudentService {
	void info();
	String addStudent(Student std);
	Student searchDetail(String sid);
	String updateDetails(Student std);
	String deleteRecord(String sid);
}
