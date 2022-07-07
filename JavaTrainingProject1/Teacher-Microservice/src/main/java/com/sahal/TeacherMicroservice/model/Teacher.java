package com.sahal.TeacherMicroservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_name")
    private String name;

    @Column(name = "teacher_age")
    private int age;

    @Column(name = "teacher_subject")
    private String subject;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd:MM:yyyy")
    @Column(name = "teacher_doj")
    @Temporal(TemporalType.DATE)
    private Date dateOfJoining;
}
