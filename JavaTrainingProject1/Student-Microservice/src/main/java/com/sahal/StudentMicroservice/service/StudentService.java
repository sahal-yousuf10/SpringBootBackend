package com.sahal.StudentMicroservice.service;

import com.sahal.StudentMicroservice.feignService.FeignService;
import com.sahal.StudentMicroservice.model.Student;
import com.sahal.StudentMicroservice.repository.StudentRepository;
import com.sahal.StudentMicroservice.valueObjects.Teacher;
import com.sahal.StudentMicroservice.valueObjects.ValueObjects;
import com.sahal.StudentMicroservice.valueObjects.ValueObjects2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FeignService feignService;

//    @Autowired
//    private RestTemplate restTemplate;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> saveListOfStudents(List<Student> studentList) {
        return studentRepository.saveAll(studentList);
    }

    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    public List<Student> findAllStudentsByHobby(String hobby) {
        return studentRepository.findStudentByHobby(hobby);
    }

//    public ValueObjects findAllStudentByTeacherName(String name) {
//        ValueObjects vo = new ValueObjects();
//        Teacher teacher = restTemplate.getForObject("http://localhost:8091/teachers/name/" + name, Teacher.class);
//        List<Student> studentList = studentRepository.findStudentByTeacherId(teacher.getId());
//        vo.setTeacher(teacher);
//        vo.setStudentList(studentList);
//        return vo;
//    }

    public Student updateStudentById(Student student) {
        return studentRepository.save(student);
    }

//    public ValueObjects2 findBothBySameAge(int age) {
//        ValueObjects2 vo = new ValueObjects2();
//        List<Student> studentList = studentRepository.findStudentByAge(age);
//        Teacher[] teacherList = restTemplate.getForObject("http://localhost:8091/teachers/age/" + age, Teacher[].class);
//        List<Teacher> list = Arrays.asList(teacherList);
//        vo.setStudentList(studentList);
//        vo.setTeacherList(list);
//        return vo;
//    }

    public List<Student> getAllStudentsBeforeTheGivenDate(String date) throws ParseException {
        Date date1 = new SimpleDateFormat("dd:MM:yyyy").parse(date);
        List<Student> studentList = studentRepository.findAll();
        return studentList
                .stream()
                .filter(x -> x.getDate_of_birth()
                        .before(date1))
                .collect(Collectors.toList());
    }

    public List<Student> getAllStudentsAfterTheGivenDate(String date) throws ParseException {
        Date date1 = new SimpleDateFormat("dd:MM:yyyy").parse(date);
        List<Student> studentList = studentRepository.findAll();
        return studentList
                .stream()
                .filter(s -> s.getDate_of_birth()
                        .after(date1))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByTeacherId(long id) {
        return studentRepository.findStudentByTeacherId(id);
    }

    public ValueObjects2 findBothBySameAge(int age) {
        List<Teacher> teacherList = feignService.findTeacherByAge(age).getBody();
        List<Student> studentList = studentRepository.findStudentByAge(age);
        ValueObjects2 vo = new ValueObjects2();
        vo.setTeacherList(teacherList);
        vo.setStudentList(studentList);
        return vo;
    }

    public ValueObjects findStudentByTeacherName(String name) {
        Teacher teacher = feignService.findTeacherByName(name).getBody();
        List<Student> studentList = studentRepository.findStudentByTeacherId(teacher.getId());
        ValueObjects vo = new ValueObjects();
        vo.setTeacher(teacher);
        vo.setStudentList(studentList);
        return vo;
    }
}
