package com.example.demo.controller;

import com.example.demo.db.entity.Competition;
import com.example.demo.db.repository.CompetitionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompetitionController {


    private final CompetitionRepository competitionRepository;

    public CompetitionController(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @GetMapping("/competitions")
    public ResponseEntity<List<Competition>> getAll() {
        List<Competition> all = competitionRepository.findAll();

        return ResponseEntity.ok(all);
    }


}
