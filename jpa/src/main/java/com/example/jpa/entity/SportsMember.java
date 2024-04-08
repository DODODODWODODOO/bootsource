package com.example.jpa.entity;

import groovy.transform.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@ToString(excludes = "locker")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SportsMember extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @SequenceGenerator(name = "sports_member_seq_gen", sequenceName = "sports_member_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sports_member_seq_gen")
    private Long id;

    private String name;

    @OneToOne
    private Locker locker;
}
