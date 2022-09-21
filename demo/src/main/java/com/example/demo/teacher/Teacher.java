package com.example.demo.teacher;

import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long id;
    private String email;
    private String name;
    @ManyToMany(cascade =
            {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "student_assignment",
                joinColumns = {@JoinColumn(name = "teacher_id")},
                inverseJoinColumns = {@JoinColumn(name="student_id")})
    private List<Student> students = new ArrayList<>();

    public Teacher(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Teacher(@JsonProperty("email") String email,
                   @JsonProperty("name") String name) {
        this.email = email;
        this.name = name;
    }

    public Teacher(String email) {
        this.email = email;
    }

    public Teacher() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addStudent(Student student)
    {
        this.students.add(student);
        student.getTeachers().add(this);
    }
    @Override
    public String toString() {
        return "Teacher{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
