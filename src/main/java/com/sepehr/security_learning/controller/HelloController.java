package com.sepehr.security_learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    
    @GetMapping("/admins")
    public String getAdmins() {
        return "Hello admin";
    }

    @GetMapping("/employees")
    public String getEmployees(){
        return "Hello employee";
    }

    @GetMapping("/user")
    public String getUser(){
        return "Hello user";
    }

    

}
