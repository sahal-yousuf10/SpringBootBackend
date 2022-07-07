package com.sahal.StudentMicroservice.feignService;

import com.sahal.StudentMicroservice.valueObjects.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "teacher-microservice", url = "http://localhost:8091/teachers", fallback = FeignFallback.class)
public interface FeignService {

    @GetMapping("/name/{name}")
    ResponseEntity<Teacher> findTeacherByName(@PathVariable String name);

    @GetMapping("/age/{age}")
    ResponseEntity<List<Teacher>> findTeacherByAge(@PathVariable int age);

    @GetMapping("/valid")
    ResponseEntity<String> validation();
}
