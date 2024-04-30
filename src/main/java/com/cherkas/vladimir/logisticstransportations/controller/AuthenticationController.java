package com.cherkas.vladimir.logisticstransportations.controller;

import com.cherkas.vladimir.logisticstransportations.controller.request.AuthenticationRequest;
import com.cherkas.vladimir.logisticstransportations.controller.request.AuthenticationResponse;
import com.cherkas.vladimir.logisticstransportations.service.AuthenticationService;
import com.cherkas.vladimir.logisticstransportations.controller.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
