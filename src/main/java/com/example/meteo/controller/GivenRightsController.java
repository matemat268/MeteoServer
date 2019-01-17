package com.example.meteo.controller;

import com.example.meteo.entity.GivenRights;
import com.example.meteo.entity.Right;
import com.example.meteo.repository.GivenRightsRepository;
import com.example.meteo.repository.RightRepository;
import com.example.meteo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class GivenRightsController {

    GivenRightsRepository givenRightsRepository;
    UserRepository userRepository;
    RightRepository rightRepository;

    @Autowired
    public GivenRightsController(GivenRightsRepository givenRightsRepository, UserRepository userRepository, RightRepository rightRepository) {
        this.givenRightsRepository = givenRightsRepository;
        this.userRepository = userRepository;
        this.rightRepository = rightRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/isAdmin/{userId}")
    public Boolean getAdmin(@PathVariable Long userId) {
        if(userId > 0) {
            if (givenRightsRepository.findAllByUzytDocelowyAndPrawo(userRepository.findById(userId).get(), rightRepository.findFirstByNazwa("ADMIN")) != null)
                return true;
            else
                return false;
        }
        return false;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/makeAdmin")
    public Boolean makeAdmin(@RequestBody Long userId) {
        if(userId > 0) {
            if(givenRightsRepository.findAllByUzytDocelowyAndPrawo(userRepository.findById(userId).get(), rightRepository.findFirstByNazwa("ADMIN")) == null)
                givenRightsRepository.save(new GivenRights(userRepository.findById(userId).get(),userRepository.findById(userId).get(), null, rightRepository.findFirstByNazwa("ADMIN"), new Date()));
            else
                givenRightsRepository.delete(givenRightsRepository.findAllByUzytDocelowyAndPrawo(userRepository.findById(userId).get(), rightRepository.findFirstByNazwa("ADMIN")));
        }
        return false;
    }
}
