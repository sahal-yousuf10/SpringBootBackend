package com.sahal.StudentMicroservice.valueObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sahal.StudentMicroservice.DTO.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    private long id;
    private String name;
    private int age;
    private String subject;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd:MM:yyyy")
    @Temporal(TemporalType.DATE)
    private Date date_of_joining;
    private ErrorDTO error;
}
