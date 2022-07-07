package com.sahal.StudentMicroservice.feignService;

import com.sahal.StudentMicroservice.DTO.ErrorDTO;
import com.sahal.StudentMicroservice.valueObjects.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class FeignFallback implements FeignService{

    @Override
    public ResponseEntity<Teacher> findTeacherByName(String name){

        log.info("fallback validation");
        Teacher teacher = new Teacher();
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Teacher service not working");
        teacher.setError(errorDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(teacher);
    }

    @Override
    public ResponseEntity<List<Teacher>> findTeacherByAge(int age){

        log.info("fallback validation");
        Teacher teacher = new Teacher();
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Teacher service not working");
        teacher.setError(errorDTO);
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacher);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(teacherList);
    }

    @Override
    public ResponseEntity<String> validation() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Teacher service not working");
    }
}
