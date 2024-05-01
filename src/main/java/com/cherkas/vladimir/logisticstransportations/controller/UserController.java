package com.cherkas.vladimir.logisticstransportations.controller;

import com.cherkas.vladimir.logisticstransportations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?>getUserData(@RequestHeader("username") String username){
        return ResponseEntity.ok(userService.loadUserByUsername(username));
    }
}
