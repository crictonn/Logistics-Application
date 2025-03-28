package com.cherkas.vladimir.logisticstransportations.controller.request;

import com.cherkas.vladimir.logisticstransportations.model.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private Roles role;
    private Long companyID;
}
