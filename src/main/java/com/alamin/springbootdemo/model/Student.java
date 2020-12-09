package com.alamin.springbootdemo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id
    private long id;
    private String name;
    private Double cgpa;

}
