package com.example.demo.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_sequence")
    @SequenceGenerator(name = "driver_sequence", allocationSize = 1)
    private Long id;

    private String name;


}
