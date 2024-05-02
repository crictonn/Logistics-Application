package com.cherkas.vladimir.logisticstransportations.controller;

import com.cherkas.vladimir.logisticstransportations.controller.request.RegisterRequest;
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
    public ResponseEntity<?> getUserData(@RequestHeader("username") String username){
        return ResponseEntity.ok(userService.loadUserByUsername(username));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("/update")
    public ResponseEntity<?> changeUserDetails(@RequestBody RegisterRequest request){
        return userService.changeUserDetails(request);
    }
}
