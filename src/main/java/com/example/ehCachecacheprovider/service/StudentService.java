package com.example.ehCachecacheprovider.service;

import java.util.List;

import com.example.ehCachecacheprovider.exception.StudentEmailExistedException;
import com.example.ehCachecacheprovider.exception.StudentNotFoundException;
import com.example.ehCachecacheprovider.model.Student;

public interface StudentService {
	
	Student findStudentById(Long id) throws StudentNotFoundException;
	List<Student> findAllStudents();
	Student saveStuedent(Student student) throws StudentEmailExistedException;
	void updateStudent(Long id, Student student) throws StudentNotFoundException, StudentEmailExistedException;
	void deleteStudent(Long id) throws StudentNotFoundException;

}
