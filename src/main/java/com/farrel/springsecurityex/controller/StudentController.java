package com.farrel.springsecurityex.controller;

import com.farrel.springsecurityex.dto.StudentRequest;
import com.farrel.springsecurityex.dto.StudentResponse;
import com.farrel.springsecurityex.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//Demo CSRF TOKEN

//Menggunakan Student

//CRSF TOKEN digunakan saat ingin mengubah atau menambah data, maka diwajibkan untuk mengirim X-CSRF-TOKEN pada Header

//hal ini digunakan untuk mencegah pihak ketifa yang "jahat" yang mencuri id session kita dan mengirim request ke server untuk melakukan hal-hal yang tidak diinginkan

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/student")
    public List<StudentResponse> getStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/student")
    public StudentResponse addStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.createStudent(studentRequest);
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
