package com.example.meteo.controller;

import com.example.meteo.entity.Log;
import com.example.meteo.entity.SystemStatistic;
import com.example.meteo.repository.LogInRepository;
import com.example.meteo.repository.LogRepository;
import com.example.meteo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class LogController {

    LogRepository logRepository;
    UserRepository userRepository;
    LogInRepository logInRepository;

    @Autowired
    public LogController(LogRepository logRepository, UserRepository userRepository, LogInRepository logInRepository) {
        this.logRepository = logRepository;
        this.userRepository = userRepository;
        this.logInRepository = logInRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/systemLogs")
    public List<Log> getLogs() {
        return logRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/log")
    public Log saveLog(@RequestBody Log log) {
        if(log.getTyp().equals("REG")) {
            log.setUzyt(userRepository.getNewest());
            log.setCzas(new Date());
            return logRepository.save(log);
        } else {
            log.setUzyt(userRepository.findById(log.getUzyt().getId_uzyt()).get());
            log.setCzas(new Date());
            return logRepository.save(log);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/systemStatistic")
    public SystemStatistic getSystemStatistic() {
        SystemStatistic ss = new SystemStatistic();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        ss.setLogInMonth(logInRepository.findLoginMonth(new Long(cal.get(Calendar.MONTH) + 1), new Long(cal.get(Calendar.YEAR))).size());
        ss.setLogInToday(logInRepository.findLoginToday(new Long(cal.get(Calendar.DAY_OF_MONTH)), new Long(cal.get(Calendar.MONTH) + 1), new Long(cal.get(Calendar.YEAR))).size());
        ss.setNewUsersMonth(logRepository.findNewUsersMonth(new Long(cal.get(Calendar.MONTH) + 1), new Long(cal.get(Calendar.YEAR))).size());
        ss.setNewUsersToday(logRepository.findNewUsersToday(new Long(cal.get(Calendar.DAY_OF_MONTH)), new Long(cal.get(Calendar.MONTH) + 1), new Long(cal.get(Calendar.YEAR))).size());
        ss.setNewestUser(userRepository.getNewest());
        return ss;
    }
}
