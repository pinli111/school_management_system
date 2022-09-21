package com.example.demo.student;

import com.example.demo.JsonToJavaObjectBean;
import com.example.demo.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping(path = "api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "api/allstudents")
    //@ResponseStatus(code = HttpStatus.OK, reason = "Get all students!")
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }

    @PostMapping(path = "api/students")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Created a new student!")
    public void registerNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "api/suspend")
    @Transactional
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Delete a student!")
    public void deleteStudent(@RequestBody JsonToJavaObjectBean jsonToJavaObjectBeann)
    {
        studentService.deleteStudent(jsonToJavaObjectBeann.getStudent());
    }

    @GetMapping(path = "api/commonstudents")
    //@ResponseStatus(code = HttpStatus.OK, reason = "Get all students!")
    public List<String> getCommonStudents(
            @RequestParam(required = true) List<String> email
    )
    {
        System.out.println(studentService.getCommonStudents(email));
        List<String> emails = studentService.getCommonStudents(email).stream().map(
                student -> student.getEmail()).collect(Collectors.toList());
        return emails;
    }

    // request param is with ?name=...
    @PutMapping(path = "{studentId}" )
    public void updateStudent(
            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
            )
    {
        studentService.updateStudent(id, name, email);
    }
}
