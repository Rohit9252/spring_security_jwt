package com.example.springjwt.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentId;
    private String studentName;
    private String email;

    private  String password;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "student_role", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role;


    public Student(String studentName, String email, String password) {
        this.studentName = studentName;
        this.email = email;
        this.password = password;
       
    }

}
