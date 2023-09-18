package com.practice.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.jpa.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

	List<Student> findByName(String name);
}
