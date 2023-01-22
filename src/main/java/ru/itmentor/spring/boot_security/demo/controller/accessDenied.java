package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class accessDenied {

    @GetMapping("accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }
}
