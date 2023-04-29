package com.gl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dao.StudentRepository;
import com.gl.entity.Student;

@Service
public class StudentServiceImple implements StudentService {
	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		
		return studentRepository.findAll();
	}

	@Override
	public Student findById(int id) {
		
		return studentRepository.findById(id).get();
	}

	@Override
	public void save(Student theStudent) {
		studentRepository.save(theStudent);
	}

	@Override
	public void deleteById(int id) {
		studentRepository.deleteById(id);
	}

}
