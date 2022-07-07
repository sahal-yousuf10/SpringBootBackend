package com.sahal.StudentMicroservice.valueObjects;

import com.sahal.StudentMicroservice.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueObjects {

    private Teacher teacher;
    private List<Student> studentList;
}
