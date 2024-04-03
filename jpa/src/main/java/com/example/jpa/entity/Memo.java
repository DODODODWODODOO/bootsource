package com.example.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity // 데이터베이스 에서 데이터로 관리하는 대상
@Table(name = "memotbl")
public class Memo {
    // AUTO : create sequence memotbl_seq start with 1 increment by 50(oracle기준)
    // IDENTITY(기본키 생성은 데이터베이스에 위임) : mno number(19,0) generated as identity
    // SEQUENCE : create sequence memotbl_seq start with 1 increment by 50
    @SequenceGenerator(name = "memo_seq_gen", sequenceName = "memo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memo_seq_gen")
    @Id // == primary key
    private Long mno;

    // 자바 변수명은 _ 사용 X => memo_text(데이터베이스 컬럼)
    @Column(nullable = false, length = 300)
    private String memoText;
}
