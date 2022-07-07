package com.sahal.TeacherMicroservice.controller;

import com.sahal.TeacherMicroservice.model.Teacher;
import com.sahal.TeacherMicroservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public ResponseEntity<List<Teacher>> getListOfTeachers(){

        List<Teacher> teacherList = teacherService.getListOfTeachers();
        return ResponseEntity.status(HttpStatus.FOUND).body(teacherList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Teacher>> getTeachersById(@PathVariable long id){

        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(teacher);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Teacher> findTeacherByName(@PathVariable String name){

        Teacher teachers = teacherService.findAllTeachersByName(name);
        return ResponseEntity.status(200).body(teachers);
    }

    @GetMapping("/valid")
    public ResponseEntity<String> validation(){
        return ResponseEntity.status(HttpStatus.OK).body("Teacher service is working");
    }

    @GetMapping("/age/{age}/subject/{subject}")
    public ResponseEntity<List<Teacher>> getAllTeachersOfSameAgeAndSubject(@PathVariable int age, @PathVariable String subject){

        List<Teacher> teacherList = teacherService.findAllBySameAgeAndSubject(age, subject);
        return ResponseEntity.status(HttpStatus.FOUND).body(teacherList);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Teacher>> getAllTeachersByAge(@PathVariable int age){

        List<Teacher> teacherList = teacherService.findAllTeachersByAge(age);
        return ResponseEntity.status(200).body(teacherList);
    }

    @PostMapping
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher){

        Teacher savedTeacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    }

    @PostMapping("/list")
    public ResponseEntity<List<Teacher>> saveListOfTeachers(@RequestBody List<Teacher> teacherList){

        List<Teacher> savedTeachersList = teacherService.saveListOfTeachers(teacherList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeachersList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacherById(@PathVariable long id, @RequestBody Teacher teacher){

        teacher.setId(id);
        Teacher updatedTeacher = teacherService.updateTeacherById(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeacherById(@PathVariable long id){

        teacherService.deleteTeacherById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
