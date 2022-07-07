package com.sahal.TeacherMicroservice.repository;

import com.sahal.TeacherMicroservice.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    //@Query(value = "SELECT * FROM teachers WHERE teacher_name = :teacherName", nativeQuery = true)
    Teacher findTeacherByName(String name);

    @Query(value = "SELECT * FROM teachers WHERE teacher_age = :teacherAge AND teacher_subject = :teacherSubject", nativeQuery = true)
    List<Teacher> findAllBySameAgeAndSubject(@Param("teacherAge") int age, @Param("teacherSubject") String subject);

    //@Query(value = "SELECT * FROM teachers WHERE teacher_age = :teacherAge", nativeQuery = true)
    List<Teacher> findTeacherByAge(int age);
}
