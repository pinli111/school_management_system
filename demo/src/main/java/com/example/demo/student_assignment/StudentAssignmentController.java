package com.example.demo.student_assignment;

import com.example.demo.JsonToJavaObjectBean;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepo;
import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentAssignmentController {
    @Autowired
    JsonToJavaObjectBean jsonToJavaObjectBean;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;
    @PostMapping(path = "api/register")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Register students to teacher!" )
    public void registerStudentsToTeacher(@RequestBody JsonToJavaObjectBean body)
    {
        Teacher specificTeacher = body.getTeacher();
        teacherRepo.findTeacherByEmail(specificTeacher.getEmail()).map(
                teacher -> {
                    String studentEmail = body.getStudent().getEmail();
                    if(studentEmail!= "")
                    {
                        Student currentStudent = studentRepo.findStudentsByEmail(studentEmail)
                                .orElseThrow(() -> new IllegalStateException("student email not found! "));
                        teacher.addStudent(currentStudent);
                        teacherRepo.save(teacher);
                        return currentStudent;
                    }
                    teacher.addStudent(body.getStudent());
                    return studentRepo.save(body.getStudent());
                }).orElseThrow(() -> new IllegalStateException("teacher email not found! "));
        System.out.println(jsonToJavaObjectBean.toString());
    }
}
