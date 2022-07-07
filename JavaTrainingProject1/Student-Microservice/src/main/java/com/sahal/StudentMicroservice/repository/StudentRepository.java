package com.sahal.StudentMicroservice.repository;

import com.sahal.StudentMicroservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentByAge(int age);
    List<Student> findStudentByHobby(String hobby);
    List<Student> findStudentByTeacherId(Long id);

}
