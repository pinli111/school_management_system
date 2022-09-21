package com.example.demo.teacher;

import com.example.demo.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Long> {

    @Query("SELECT t FROM Teacher t WHERE t.email = ?1")
    Optional<Teacher> findTeacherByEmail(String email);
}
