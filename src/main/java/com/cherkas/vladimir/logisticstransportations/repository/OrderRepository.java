package com.cherkas.vladimir.logisticstransportations.repository;

import com.cherkas.vladimir.logisticstransportations.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findOrderById(Long id);
    Optional<Order> findOrderByOrderName(String name);
    List<Order> findOrderByUserID(Long id);
    List<Order> findOrdersByCompanyID(Long id);
}
