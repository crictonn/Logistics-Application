package com.cherkas.vladimir.logisticstransportations.service;

import com.cherkas.vladimir.logisticstransportations.controller.request.OrderRequest;
import com.cherkas.vladimir.logisticstransportations.model.Order;
import com.cherkas.vladimir.logisticstransportations.repository.OrderRepository;
import com.cherkas.vladimir.logisticstransportations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CargoService cargoService;
    private final RouteSegmentService routeSegmentService;
    private final UserRepository userRepository;

    public ResponseEntity<?> createOrder(OrderRequest request) {
        var order = Order.builder()
                .orderName(request.getName())
                .departurePoint(request.getDeparturePoint())
                .destinationPoint(request.getDestinationPoint())
                .routeLength(99.9)
                .regDate(new Date(System.currentTimeMillis()))
                .arrivalDate(new Date(System.currentTimeMillis()+(1000*60*60*24*7)))
                .price(99.9)
                .status("Pending")
                .userID(userRepository.findUserByUsername(request.getUsername()).get().getId())
                .build();
        orderRepository.save(order);
        if(!request.getAdditionalPoint1().equals("") || !request.getAdditionalPoint2().equals("")){
            routeSegmentService.parseRouteSegments(request);
        }
        cargoService.saveCargo(request);

        return ResponseEntity.ok("Order added");
    }

    public List<Order> getAllOrders(String username){
        return orderRepository.findOrderByUserID(userRepository.findUserByUsername(username).get().getId());
    }
}
