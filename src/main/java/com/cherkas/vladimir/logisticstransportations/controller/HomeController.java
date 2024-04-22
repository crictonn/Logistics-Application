package com.cherkas.vladimir.logisticstransportations.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/home")
public class HomeController {
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("hello");
    }
}
