package com.example.meteo.controller;

import com.example.meteo.repository.LogInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {

    LogInRepository logInRepository;

    @Autowired
    public LogInController(LogInRepository logInRepository) {
        this.logInRepository = logInRepository;
    }
}
