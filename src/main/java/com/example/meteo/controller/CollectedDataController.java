package com.example.meteo.controller;

import com.example.meteo.repository.CollectedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectedDataController {

    CollectedDataRepository collectedDataRepository;

    @Autowired
    public CollectedDataController(CollectedDataRepository collectedDataRepository) {
        this.collectedDataRepository = collectedDataRepository;
    }
}
