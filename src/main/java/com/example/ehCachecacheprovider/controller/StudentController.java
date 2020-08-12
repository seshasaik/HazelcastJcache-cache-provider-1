package com.example.ehCachecacheprovider.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ehCachecacheprovider.model.Student;
import com.example.ehCachecacheprovider.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		// TODO Auto-generated constructor stub
		this.studentService = studentService;
	}

	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		return new ResponseEntity<List<Student>>(studentService.findAllStudents(), HttpStatus.OK);

	}

	@GetMapping("/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "studentId") Long studentId) {
		return new ResponseEntity<Student>(studentService.findStudentById(studentId), HttpStatus.OK);

	}
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.saveStuedent(student), HttpStatus.OK);

	}
	
	@PutMapping("/{studentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void udpateStudentById(@PathVariable(value = "studentId") Long studentId, @RequestBody Student student) {
		studentService.updateStudent(studentId, student);	
	}
	
	@DeleteMapping("/{studentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStudentById(@PathVariable(value = "studentId") Long studentId) {
		studentService.deleteStudent(studentId);	
	}
	 
}
