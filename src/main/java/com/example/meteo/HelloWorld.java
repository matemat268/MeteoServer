package com.example.meteo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping("/zz")
    public String index(){
        return "Hello Worldzz";
    }
}
