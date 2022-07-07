package com.sahal.StudentMicroservice.controller;

import com.sahal.StudentMicroservice.feignService.FeignService;
import com.sahal.StudentMicroservice.model.Student;
import com.sahal.StudentMicroservice.service.StudentService;
import com.sahal.StudentMicroservice.valueObjects.Teacher;
import com.sahal.StudentMicroservice.valueObjects.ValueObjects;
import com.sahal.StudentMicroservice.valueObjects.ValueObjects2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FeignService feignService;

    @GetMapping("/list")
    public ResponseEntity<List<Student>> getListOfStudents(){

        List<Student> studentList = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.FOUND).body(studentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable long id){

        Optional<Student> student = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(student);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Student>> getStudentsByAge(@PathVariable int age){

        List<Student> studentList = studentService.findStudentByAge(age);
        return ResponseEntity.status(HttpStatus.FOUND).body(studentList);
    }

    @GetMapping("/hobby/{hobby}")
    public ResponseEntity<List<Student>> getAllStudentsByHobby(@PathVariable String hobby){

        List<Student> studentList = studentService.findAllStudentsByHobby(hobby);
        return ResponseEntity.status(HttpStatus.FOUND).body(studentList);
    }

//    @GetMapping("/teacher/{name}")
//    public ResponseEntity<ValueObjects> getAllStudentsByTeacherName(@PathVariable String name){
//        ValueObjects valueObjects = studentService.findAllStudentByTeacherName(name);
//        return ResponseEntity.status(HttpStatus.FOUND).body(valueObjects);
//    }

    @GetMapping("/teacher/{name}")
    public ResponseEntity<ValueObjects> findStudentsByTeacherName(@PathVariable String name){

        ValueObjects vo = studentService.findStudentByTeacherName(name);
        return ResponseEntity.status(200).body(vo);

//        Teacher teacher = feignService.findTeacherByName(teacherName).getBody();
//        //ResponseEntity<Teacher> teacherResponseEntity = feignService.findTeacherByName(teacherName);
//        List<Student> studentList = studentService.getStudentsByTeacherId(teacher.getId());
//        ValueObjects vo = new ValueObjects();
//        vo.setTeacher(teacher);
//        vo.setStudentList(studentList);
//        return ResponseEntity.status(201).body(vo);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> validation(){

        return feignService.validation();
    }
    @GetMapping("/both/age/{age}")
    public ResponseEntity<ValueObjects2> findStudentsAndTeachersBySameAge(@PathVariable int age){

        ValueObjects2 vo = studentService.findBothBySameAge(age);
        return ResponseEntity.status(200).body(vo);

//        List<Teacher> teacherList = feignService.findTeacherByAge(age).getBody();
//        List<Student> studentList = studentService.findStudentByAge(age);
//        ValueObjects2 vo = new ValueObjects2();
//        vo.setTeacherList(teacherList);
//        vo.setStudentList(studentList);
//        return ResponseEntity.status(201).body(vo);


    }
//
//    @GetMapping("/both/age/{age}")
//    public ResponseEntity<ValueObjects2> getAllTeachersAndStudentsOfSameAge(@PathVariable int age){
//
//        ValueObjects2 valueObjects2 = studentService.findBothBySameAge(age);
//        return ResponseEntity.status(HttpStatus.FOUND).body(valueObjects2);
//    }

    @GetMapping("/{condition}/{date}")
    public ResponseEntity<List<Student>> getAllStudentsBeforeAndAfterTheGivenDate(@PathVariable String condition , @PathVariable String date) throws ParseException {

        if (condition.equals("before")){

            List<Student> studentList = studentService.getAllStudentsBeforeTheGivenDate(date);
            return ResponseEntity.status(HttpStatus.FOUND).body(studentList);
        }

        List<Student> studentList = studentService.getAllStudentsAfterTheGivenDate(date);
        return ResponseEntity.status(HttpStatus.FOUND).body(studentList);

    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){

        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PostMapping("/list")
    public ResponseEntity<List<Student>> saveListOfStudents(@RequestBody List<Student> studentList){

        List<Student> savedStudentsList = studentService.saveListOfStudents(studentList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudentsList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable long id, @RequestBody Student student){

        student.setId(id);
        Student updatedStudent = studentService.updateStudentById(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudentById(@PathVariable long id){

        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
