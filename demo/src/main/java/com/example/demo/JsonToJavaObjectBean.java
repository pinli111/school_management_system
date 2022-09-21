package com.example.demo;

import com.example.demo.student.Student;
import com.example.demo.teacher.Teacher;
import org.springframework.stereotype.Component;

@Component
public class JsonToJavaObjectBean {
    private Student student;
    private Teacher teacher;

    public JsonToJavaObjectBean() {
    }

    public JsonToJavaObjectBean(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "JsonToJavaObjectBean{" +
                "student=" + student +
                '}';
    }
}
