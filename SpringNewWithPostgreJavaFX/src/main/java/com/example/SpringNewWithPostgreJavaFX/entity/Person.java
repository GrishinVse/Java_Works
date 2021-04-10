package com.example.SpringNewWithPostgreJavaFX.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String city;
    private String street;

    private Integer postalCode;
    private Date birthday;
    //@JsonFormat(pattern = "dd.MM.yyyy")
    //private LocalDate birthday; // java.util.Date date format 'yyyy-MM-dd'T'HH:mm:ss.SSSX' EXAMPLE "2015-10-01T15:44:00.000Z"

}
