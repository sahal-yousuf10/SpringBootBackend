package com.sahal.StudentMicroservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_age")
    private int age;

    @Column(name = "student_hobby")
    private String hobby;

    @Column(name = "student_roll_no", unique = true)
    private int roll_no;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd:MM:yyyy")
    @Column(name = "student_dob")
    @Temporal(TemporalType.DATE)
    private Date date_of_birth;

    @Column(name = "teacher_id")
    private long teacherId;

}
