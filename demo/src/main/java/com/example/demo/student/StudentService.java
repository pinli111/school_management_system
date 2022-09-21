package com.example.demo.student;

import com.example.demo.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudents()
    {
        return studentRepo.findAll();
    }
    public List<Student> getCommonStudents(List<String> email)
    {
        List<Long> teacherId = studentRepo.findTeacherByEmail(email);
        return studentRepo.findStudentsByTeacherId(teacherId);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentsByEmail = studentRepo.findStudentsByEmail(student.getEmail());
        if(studentsByEmail.isPresent())
        {
            throw new IllegalStateException("Email taken!");
        }
        studentRepo.save(student);
    }


    public void deleteStudent(Student student) {
        Optional<Student> studentsByEmail = studentRepo.findStudentsByEmail(student.getEmail());
        if(!studentsByEmail.isPresent())
        {
            throw new IllegalStateException("Student with email" + student.getEmail() + " not exist!");
        }
        studentRepo.deleteStudentsByEmail(student.getEmail());
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepo.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "student with id" + id + "not found!"
        ));
        if(name!= null && name.length()>0 && !Objects.equals(student.getName(),name))
        {
            student.setName(name);
        }
        if(email!= null && email.length()>0 && !Objects.equals(student.getEmail(),email))
        {
            student.setEmail(email);
        }
        studentRepo.save(student);
    }
}
