package com.sahal.TeacherMicroservice.service;

import com.sahal.TeacherMicroservice.model.Teacher;
import com.sahal.TeacherMicroservice.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getListOfTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(long id) {
        return teacherRepository.findById(id);
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> saveListOfTeachers(List<Teacher> teacherList) {
        return teacherRepository.saveAll(teacherList);
    }

    public void deleteTeacherById(long id) {
        teacherRepository.deleteById(id);
    }

    public Teacher findAllTeachersByName(String name) {
        return teacherRepository.findTeacherByName(name);
    }

    public List<Teacher> findAllBySameAgeAndSubject(int age, String subject) {
        return teacherRepository.findAllBySameAgeAndSubject(age, subject);
    }

    public List<Teacher> findAllTeachersByAge(int age) {
        return teacherRepository.findTeacherByAge(age);
    }

    public Teacher updateTeacherById(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
