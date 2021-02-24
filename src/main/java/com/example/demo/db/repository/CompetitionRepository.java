package com.example.demo.db.repository;

import com.example.demo.db.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    List<Competition> findAllByPlace(Integer place);

}
