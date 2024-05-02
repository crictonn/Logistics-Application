package com.cherkas.vladimir.logisticstransportations.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    private String name;
    private String description;
    private String email;
    private String phone;
}
