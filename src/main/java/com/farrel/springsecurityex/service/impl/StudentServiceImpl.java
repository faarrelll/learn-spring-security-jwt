package com.farrel.springsecurityex.service.impl;

import com.farrel.springsecurityex.dto.StudentRequest;
import com.farrel.springsecurityex.dto.StudentResponse;

import com.farrel.springsecurityex.entity.Student;
import com.farrel.springsecurityex.repository.StudentRepository;
import com.farrel.springsecurityex.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {

        Student student = Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .build();

        studentRepository.saveAndFlush(student);

        return toStudentResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream().map(StudentServiceImpl::toStudentResponse).collect(Collectors.toList());
    }

    private static StudentResponse toStudentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .age(student.getAge())
                .build();
    }
}
