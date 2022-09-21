package com.example.demo.student;

import com.example.demo.teacher.Teacher;
import com.example.demo.teacher.TeacherRepo;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo repo, TeacherRepo teacherRepo)
    {
        return args -> {

            Student william = new Student(
                    "William",
                    "william@gmail.com"
            );

            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com"
            );

            Teacher teacher1 = new Teacher(
                    "teacher1@gmail.com",
                    "Teacher1"
            );

            Teacher teacher2 = new Teacher(
                    "teacher2@gmail.com",
                    "Teacher2"
            );

            repo.saveAll(
                    List.of(william,alex)
            );
            teacherRepo.saveAll(
                    List.of(teacher1, teacher2)
            );
        };
    }
}
