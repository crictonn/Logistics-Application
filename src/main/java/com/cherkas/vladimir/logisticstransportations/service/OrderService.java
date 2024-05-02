package com.cherkas.vladimir.logisticstransportations.service;

import com.cherkas.vladimir.logisticstransportations.controller.request.OrderRequest;
import com.cherkas.vladimir.logisticstransportations.model.Order;
import com.cherkas.vladimir.logisticstransportations.repository.CompanyRepository;
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
        Double price = request.getHeight()*request.getWidth()*request.getLength()*request.getWeight();
        if(request.getFragile())
            price*=1.5;
        if(request.getDangerous())
            price*=3;
        if(request.getTransportType()!=null)
            price*= Double.valueOf(request.getTransportType());
        var order = Order.builder()
                .orderName(request.getName())
                .departurePoint(request.getDeparturePoint())
                .destinationPoint(request.getDestinationPoint())
                .routeLength(99.9)
                .regDate(new Date(System.currentTimeMillis()))
                .arrivalDate(new Date(System.currentTimeMillis()+(1000*60*60*24*7)))
                .price(price)
                .status("Pending")
                .fragile(request.getFragile())
                .dangerous(request.getDangerous())
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
        if(userRepository.findUserByUsername(username).get().getCompanyID()==null) {
            return orderRepository.findOrderByUserID(userRepository.findUserByUsername(username).get().getId());
        }else{
            Long compID = userRepository.findUserByUsername(username).get().getCompanyID();
            return orderRepository.findOrdersByCompanyID(compID);
        }
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
