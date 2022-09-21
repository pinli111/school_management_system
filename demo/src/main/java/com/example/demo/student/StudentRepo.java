package com.example.demo.student;

import com.example.demo.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentsByEmail(String email);

    @Query("SELECT t.id FROM Teacher t WHERE t.email in ?1")
    List<Long> findTeacherByEmail(List<String> emails);

    @Query(value = "SELECT * FROM student s LEFT JOIN student_assignment a ON s.id = a.student_id WHERE a.teacher_id in ?1", nativeQuery = true)
    List<Student> findStudentsByTeacherId(List<Long> teacherId);
    
    @Modifying
    @Query("DELETE FROM Student s WHERE s.email = ?1")
    void deleteStudentsByEmail(String email);
}
