package com.cherkas.vladimir.logisticstransportations.controller;

import com.cherkas.vladimir.logisticstransportations.controller.request.OrderRequest;
import com.cherkas.vladimir.logisticstransportations.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticated")
@RequiredArgsConstructor
@CrossOrigin
public class OrdersController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(){
        return ResponseEntity.ok("Все хорошо");
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request){
        return orderService.createOrder(request);
    }
}
