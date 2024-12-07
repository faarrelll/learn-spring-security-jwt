package com.farrel.springsecurityex.repository;

import com.farrel.springsecurityex.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
