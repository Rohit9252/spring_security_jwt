package com.example.springjwt.repository;

import com.example.springjwt.model.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    public Optional<Student> findByEmail(String email );

}
