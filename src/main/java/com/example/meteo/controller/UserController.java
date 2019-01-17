package com.example.meteo.controller;

import com.example.meteo.entity.*;
import com.example.meteo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    UserRepository userRepository;
    LogInRepository logInRepository;
    RightRepository rightRepository;
    GivenRightsRepository givenRightsRepository;
    LogRepository logRepository;

    @Autowired
    public UserController(UserRepository userRepository, LogInRepository logInRepository, RightRepository rightRepository, GivenRightsRepository givenRightsRepository, LogRepository logRepository) {
        this.userRepository = userRepository;
        this.logInRepository = logInRepository;
        this.rightRepository = rightRepository;
        this.givenRightsRepository = givenRightsRepository;
        this.logRepository = logRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/login/{login}/{email}/{haslo}")
    public User login(@PathVariable String login, @PathVariable String email, @PathVariable String haslo){
        User toReturn = new User();
        if (!email.equals("null")) {
            User userLogged = userRepository.findByEmail(email);
            if(userLogged != null) {
                if(userLogged.getHaslo().equals(haslo)){
                    toReturn = userLogged;
                    logInRepository.save(new LogIn(userRepository.findById(userLogged.getId_uzyt()).get(), new Date(), new Integer(1)));
                } else {
                    toReturn.setHaslo("ERROR");
                }
            } else {
                toReturn.setEmail("ERROR");
            }
        } else {
            if (!login.equals("null")) {
                User userLogged = userRepository.findByLogin(login);
                if(userLogged != null) {
                    if(userLogged.getHaslo().equals(haslo)){
                        toReturn = userLogged;
                        logInRepository.save(new LogIn(userRepository.findById(userLogged.getId_uzyt()).get(), new Date(), new Integer(1)));
                    } else {
                        toReturn.setHaslo("ERROR");
                    }
                } else {
                    toReturn.setLogin("ERROR");
                }
            }
        }
        return toReturn;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        if(userRepository.findByLogin(user.getLogin()) == null) {
            if (userRepository.findByEmail(user.getEmail()) == null) {
                if (userRepository.findAll().isEmpty()) {
                    User u = userRepository.save(user);
                    if(rightRepository.findFirstByNazwa("ADMIN") == null)
                    {
                        givenRightsRepository.save(new GivenRights(u, u, null, rightRepository.save(new Right("ADMIN", "Prawo admina")), new Date()));
                    } else {
                        givenRightsRepository.save(new GivenRights(u, u, null, rightRepository.findFirstByNazwa("ADMIN"), new Date()));
                    }
                    return u;
                } else {
                    return userRepository.save(user);
                }
            } else {
                User toReturn = new User();
                toReturn.setEmail("ERROR");
                return toReturn;
            }
        } else {
            User toReturn = new User();
            toReturn.setLogin("ERROR");
            return toReturn;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/changeUserData")
    public User changeUserData(@RequestBody User user) {
        User oldUser = this.userRepository.findById(user.getId_uzyt()).get();
        if(oldUser != null) {
            oldUser.setImie(user.getImie());
            oldUser.setNazwisko(user.getNazwisko());
            oldUser.setEmail(user.getEmail());
            oldUser.setLogin(user.getLogin());
            this.userRepository.save(oldUser);
            return oldUser;
        }
        return user;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/changeUserPassword")
    public User changeUserPassword(@RequestBody User user) {
        User oldUser = this.userRepository.findById(user.getId_uzyt()).get();
        if(oldUser != null) {
            oldUser.setHaslo(user.getHaslo());
            this.userRepository.save(oldUser);
            return oldUser;
        }
        return user;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteUser/{id}")
    public Boolean deleteUser(@PathVariable  Long id) {
        if(id > 0) {
            userRepository.delete(userRepository.getOne(id));
            return true;
        }
        return false;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/usersInfo")
    public List<UserInfo> getUsersInfo(){
        List<User> listUsers = userRepository.findAll();
        List<UserInfo> listUsersInfo = new ArrayList<>();
        for(User u : listUsers) {
            String admin = givenRightsRepository.findAllByUzytDocelowyAndPrawo(u, rightRepository.findFirstByNazwa("ADMIN")) != null ? "Tak" : "Nie";
            listUsersInfo.add(new UserInfo(u.getId_uzyt(), u.getLogin(), u.getEmail(), u.getImie(), u.getNazwisko(), u.getPlec(), u.getData_ur(),
                    logRepository.findLogByUzytAndAndTyp(u, "REG") != null ? logRepository.findLogByUzytAndAndTyp(u, "REG").getCzas() : null,
                    logInRepository.findMaxDate(u.getId_uzyt()),
                    admin));
        }
        return listUsersInfo;
    }
}
