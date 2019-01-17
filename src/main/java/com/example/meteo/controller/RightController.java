package com.example.meteo.controller;

import com.example.meteo.repository.RightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RightController {

    RightRepository rightRepository;

    @Autowired
    public RightController(RightRepository rightRepository) {
        this.rightRepository = rightRepository;
    }
}
