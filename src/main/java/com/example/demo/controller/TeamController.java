package com.example.demo.controller;

import com.example.demo.db.entity.Competition;
import com.example.demo.db.repository.CompetitionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TeamController {


    private final CompetitionRepository competitionRepository;

    public TeamController(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @GetMapping("/teams/byWinCount")
    public ResponseEntity<Map<String, Long>> countPlaces(@RequestParam("place") Integer place) {
        List<Competition> allCompetitions = competitionRepository.findAllByPlace(place);
        Map<String, Long> teamToTeamPlaceTakenCount = allCompetitions.stream()
                .filter(competition -> place.equals(competition.getPlace()))
                .collect(Collectors.groupingBy(competition -> competition.getTeam().getName(), Collectors.counting()));

        return ResponseEntity.ok(teamToTeamPlaceTakenCount);
    }

}
