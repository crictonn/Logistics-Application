package com.cherkas.vladimir.logisticstransportations.controller;

import com.cherkas.vladimir.logisticstransportations.controller.request.CompanyRequest;
import com.cherkas.vladimir.logisticstransportations.model.Company;
import com.cherkas.vladimir.logisticstransportations.repository.CompanyRepository;
import com.cherkas.vladimir.logisticstransportations.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllComps());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCompany(@RequestBody CompanyRequest request){
        return companyService.createCompany(request);
    }
}
