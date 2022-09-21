package com.example.demo.teacher;

import com.example.demo.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;

    @Autowired
    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public void addNewTeacher(Teacher teacher)
    {
        System.out.println("add new teacher");
        Optional<Teacher> teacherByEmail = teacherRepo.findTeacherByEmail(teacher.getEmail());
        if(teacherByEmail.isPresent())
        {
            throw new IllegalStateException("Email taken!");
        }
        teacherRepo.save(teacher);
    }

}
