package com.cherkas.vladimir.logisticstransportations.controller.request;

import com.cherkas.vladimir.logisticstransportations.jwt.JwtCore;
import com.cherkas.vladimir.logisticstransportations.model.enums.Roles;
import com.cherkas.vladimir.logisticstransportations.model.User;
import com.cherkas.vladimir.logisticstransportations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtCore jwtCore;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .first_name(request.getFirstName())
                .last_name(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Roles.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtCore.generateToken(user);

        return AuthenticationResponse.builder()
                .jwt(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtCore.generateToken(user);

        return AuthenticationResponse.builder()
                .jwt(jwtToken)
                .build();
    }
}
