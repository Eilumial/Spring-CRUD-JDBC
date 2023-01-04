package com.repository;

import com.bean.Student;

public interface StudentRepo {
	void info();
	String insert(Student std);
	Student search(String sid);
	String update(Student std);
	String delete(String sid);
}
