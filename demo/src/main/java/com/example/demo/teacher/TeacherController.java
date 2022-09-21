package com.example.demo.teacher;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(path = "api/teacher")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Created a new teacher!")
    public void addNewTeacher(@RequestBody Teacher teacher)
    {
        System.out.println("controller");
        teacherService.addNewTeacher(teacher);
    }
}
