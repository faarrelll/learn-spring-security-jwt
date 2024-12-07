package com.farrel.springsecurityex.service;

import com.farrel.springsecurityex.dto.StudentRequest;
import com.farrel.springsecurityex.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentRequest studentRequest);
    List<StudentResponse> getAllStudents();
}
