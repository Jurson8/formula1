package com.example.demo.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Competition {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competition_sequence")
    @SequenceGenerator(name = "competition_sequence", allocationSize = 1)
    private Long id;

    private Integer season;

    private Integer place;

    @OneToOne
    private Driver driver;

    @OneToOne
    private Team team;

}
