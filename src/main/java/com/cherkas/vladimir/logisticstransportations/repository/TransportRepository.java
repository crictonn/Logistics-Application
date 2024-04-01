package com.cherkas.vladimir.logisticstransportations.repository;

import com.cherkas.vladimir.logisticstransportations.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    Optional<Transport> findTransportById(Long id);
    List<Transport> findTransportsByCostPerKM(Double cost);
    List<Transport> findTransportsByAverageTransportationSpeed(Double speed);
    List<Transport> findTransportsByType(String type);
    List<Transport> findTransportsByCompanyID(Long id);

}
