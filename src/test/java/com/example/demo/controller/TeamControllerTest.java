package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class TeamControllerTest {

    @Autowired
    TeamController teamController;

    @Test
    public void countPlacesTest() {
        ResponseEntity<Map<String, Long>> mapResponseEntity = teamController.countPlaces(1);
        Map<String, Long> body = mapResponseEntity.getBody();
        assertEquals(2, body.get("Mercedes"));
        log.info("Mercedes won competition in the years 2008-2015 2 times!");
    }

}