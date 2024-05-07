package com.cherkas.vladimir.logisticstransportations.service;

import com.cherkas.vladimir.logisticstransportations.controller.request.OrderRequest;
import com.cherkas.vladimir.logisticstransportations.model.Cargo;
import com.cherkas.vladimir.logisticstransportations.model.Order;
import com.cherkas.vladimir.logisticstransportations.repository.CompanyRepository;
import com.cherkas.vladimir.logisticstransportations.repository.OrderRepository;
import com.cherkas.vladimir.logisticstransportations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CargoService cargoService;
    private final RouteSegmentService routeSegmentService;
    private final UserRepository userRepository;

    public ResponseEntity<?> createOrder(OrderRequest request) {
        double price = (request.getWidth() * request.getLength()) * request.getWeight() + request.getHeight() * 10;
//        Double price = request.getHeight()*request.getWidth()*request.getLength()*request.getWeight();
        if(request.getFragile())
            price*=1.25;
        if(request.getDangerous())
            price*=1.5;
        if(request.getTransportType()!=null)
            price*= (Double.valueOf(request.getTransportType())/2);
        var order = Order.builder()
                .orderName(request.getName())
                .departurePoint(request.getDeparturePoint())
                .destinationPoint(request.getDestinationPoint())
                .routeLength(99.9)
                .regDate(new Date(System.currentTimeMillis()))
                .arrivalDate(new Date(System.currentTimeMillis()+(1000*60*60*24*7)))
                .price(price)
                .profit(price/request.getWeight())
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

    public List<Order> getOptimalOrders(String username){
        int currentWeight = 0;
        int maxWeight = 500;
        List<Order> ordersToOutput = new ArrayList<>();
        List<Order> orders = getAllOrders(username);
        List<Cargo> cargos = new ArrayList<>();

        for(Order order: orders){
            cargos.add(cargoService.getCargo(order.getId()));
        }
        for(Cargo cargo: cargos){
            if(currentWeight+cargo.getWeight() < maxWeight){
                currentWeight+=cargo.getWeight();
                ordersToOutput.add(orderRepository.findOrderById(cargo.getOrderID()).orElseThrow());
            }
        }

        return ordersToOutput;
    }

    public List<Order> getAllOrders(String username){
        if(userRepository.findUserByUsername(username).get().getCompanyID()==null) {
            List<Order> test = orderRepository.findAllByUserID(userRepository.findUserByUsername(username).get().getId());
            return test;
        }else{
            Long compID = userRepository.findUserByUsername(username).get().getCompanyID();
            return orderRepository.findOrdersByCompanyIDOrderByProfitDesc(compID);
        }
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public String changeStatus(Long id, String username) {
        Order order = orderRepository.findOrderById(id).orElseThrow();
        order.setStatus("Accepted");
        order.setCompanyID(userRepository.findUserByUsername(username).get().getCompanyID());
        orderRepository.save(order);
        return "Message: daijoubou";
    }
}
