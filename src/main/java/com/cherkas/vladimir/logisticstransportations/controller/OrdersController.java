package com.cherkas.vladimir.logisticstransportations.controller;

import com.cherkas.vladimir.logisticstransportations.controller.request.OrderRequest;
import com.cherkas.vladimir.logisticstransportations.model.Order;
import com.cherkas.vladimir.logisticstransportations.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authenticated")
@RequiredArgsConstructor
@CrossOrigin
public class OrdersController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(@RequestHeader("username")String username){
        return ResponseEntity.ok(orderService.getAllOrders(username));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders(){
        List<Order> test = orderService.getAllOrders();
        return ResponseEntity.ok(test);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request){
        return orderService.createOrder(request);
    }

    @GetMapping("/optimal")
    public ResponseEntity<?> getOptimalOrders(@RequestHeader("username")String username){
        return ResponseEntity.ok(orderService.getOptimalOrders(username));
    }

    @PostMapping("order/change")
    public ResponseEntity<?> changeStatusToAccepted(@RequestHeader Long id, @RequestHeader String username){
        return ResponseEntity.ok(orderService.changeStatus(id, username));
    }
}
