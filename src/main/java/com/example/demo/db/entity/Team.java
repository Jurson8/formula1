package com.example.demo.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_sequence")
    @SequenceGenerator(name = "team_sequence", allocationSize = 1)
    private Long id;

    private String name;

}
