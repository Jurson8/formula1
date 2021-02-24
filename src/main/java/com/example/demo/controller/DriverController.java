package com.example.demo.controller;

import com.example.demo.db.entity.Driver;
import com.example.demo.db.repository.DriverRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {


    private final DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAll() {
        List<Driver> all = driverRepository.findAll();
        return ResponseEntity.ok(all);
    }


}
