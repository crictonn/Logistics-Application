package com.cherkas.vladimir.logisticstransportations.repository;

import com.cherkas.vladimir.logisticstransportations.model.Company;
import com.cherkas.vladimir.logisticstransportations.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findCompanyById(Long id);
    List<Company> findCompaniesByAvailableTransportsContaining(Transport transport);
}
