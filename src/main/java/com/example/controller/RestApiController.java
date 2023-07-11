package com.example.controller;

import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RestApiController {

    @GetMapping("/home")
    public String home(){

        return "home";
    }
    @PostMapping("/token")
    public String token(){

        return "token";
    }

}
