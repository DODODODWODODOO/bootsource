package com.example.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Test {

    @Id
    private Long id;

    @Column(columnDefinition = "number(8)")
    private long id2;
    private int id3;
    private Integer id4;

    // id number(19,0) not null,
    // id2 number(19,0) not null,
    // id3 number(10,0) not null,
    // id4 number(10,0)

    // 기본 : int, long, boolean, char, float, double => null 대입 불가
    // 객체 : Integer, Long, Boolean,.... => null 대입 가능
}
