package com.cherkas.vladimir.logisticstransportations.service;

import com.cherkas.vladimir.logisticstransportations.controller.request.RegisterRequest;
import com.cherkas.vladimir.logisticstransportations.model.User;
import com.cherkas.vladimir.logisticstransportations.model.enums.Roles;
import com.cherkas.vladimir.logisticstransportations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));
        return User.build(user);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public ResponseEntity<?> changeUserDetails(RegisterRequest request){
        User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        var newUser = User.builder()
                .id(user.getId())
                .first_name(request.getFirstName())
                .last_name(request.getLastName())
                .username(request.getUsername())
                .email(user.getEmail())
                .role(request.getRole())
                .companyID(request.getCompanyID())
                .build();
        userRepository.delete(user);
        userRepository.save(newUser);
        return ResponseEntity.ok("User changed");
    }
}
