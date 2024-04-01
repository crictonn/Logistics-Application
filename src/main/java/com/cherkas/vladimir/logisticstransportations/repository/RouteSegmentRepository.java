package com.cherkas.vladimir.logisticstransportations.repository;

import com.cherkas.vladimir.logisticstransportations.model.RouteSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteSegmentRepository extends JpaRepository<RouteSegment, Long> {
    Optional<RouteSegment> findRouteSegmentById(Long id);
    List<RouteSegment> findRouteSegmentsByOrderID(Long id);
}
