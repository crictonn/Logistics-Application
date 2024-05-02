package com.cherkas.vladimir.logisticstransportations.service;

import com.cherkas.vladimir.logisticstransportations.controller.request.CompanyRequest;
import com.cherkas.vladimir.logisticstransportations.model.Company;
import com.cherkas.vladimir.logisticstransportations.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public ResponseEntity<?> createCompany(CompanyRequest request){
        var company = Company.builder()
                .name(request.getName())
                .description(request.getDescription())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        companyRepository.save(company);

        return ResponseEntity.ok("Company added");
    }

    public List<Company> getAllComps(){
        return companyRepository.findAll();
    }
}
