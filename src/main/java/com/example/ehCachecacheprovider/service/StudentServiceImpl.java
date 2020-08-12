package com.example.ehCachecacheprovider.service;

import java.util.List;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ehCachecacheprovider.exception.StudentEmailExistedException;
import com.example.ehCachecacheprovider.exception.StudentNotFoundException;
import com.example.ehCachecacheprovider.model.Student;
import com.example.ehCachecacheprovider.repositoyr.StudentRepository;

@Service
@Transactional
@CacheDefaults(cacheName = "student")
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	private final String STUDENT_NOT_FOUND = "Student with id : %d is not found";

	private final String STUDENT_WITH_EMAIL_FOUND = "Student with email : %s is exist";

	public StudentServiceImpl(StudentRepository studentRepository) {
		// TODO Auto-generated constructor stub
		this.studentRepository = studentRepository;
	}

	@Override
	@Transactional(readOnly = true)
	@CacheResult
	public Student findStudentById(@CacheKey Long id) throws StudentNotFoundException {
		// TODO Auto-generated method stub
		return getStudent(id);
	}

	@Override	
	@CacheResult(cacheName = "allStudents")
	public List<Student> findAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Student saveStuedent(Student student) throws StudentEmailExistedException {
		// TODO Auto-generated method stub
		try {
			removeAllStudents();
			return studentRepository.save(student);
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new StudentEmailExistedException(String.format(STUDENT_WITH_EMAIL_FOUND, student.getEmail()));
		}

	}

	@Override
	@CachePut(noCacheFor = {StudentNotFoundException.class, StudentEmailExistedException.class})
	public void updateStudent(@CacheKey Long id, @CacheValue Student student) throws StudentNotFoundException, StudentEmailExistedException {
		// TODO Auto-generated method stub
		getStudent(id);
		try {
			studentRepository.save(student);
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new StudentEmailExistedException(String.format(STUDENT_WITH_EMAIL_FOUND, student.getEmail()));
		}
	}

	@Override
	@CacheRemove(noEvictFor = {StudentNotFoundException.class})
	public void deleteStudent(@CacheKey Long id) throws StudentNotFoundException {
		// TODO Auto-generated method stub
		getStudent(id);
		studentRepository.delete(getStudent(id));
		removeAllStudents();
	}

	private Student getStudent(Long studentId) {
		return studentRepository.findById(studentId).orElseThrow(() -> {
			throw new StudentNotFoundException(String.format(STUDENT_NOT_FOUND, studentId));
		});
	}
	
	@CacheRemoveAll(cacheName = "allStudents")
	public void removeAllStudents() {
		
	}

}
