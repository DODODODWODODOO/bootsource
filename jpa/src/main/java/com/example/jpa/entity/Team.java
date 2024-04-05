package com.example.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString(exclude = { "members" }) // ToString 생성 시 클래스 내 모든 property 가 기준임
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {

    @Id
    @Column(name = "team_id")
    private String id;

    @Column(name = "team_name")
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<TeamMember> members = new ArrayList<>();

}
