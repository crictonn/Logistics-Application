package com.cherkas.vladimir.logisticstransportations.service;

import com.cherkas.vladimir.logisticstransportations.controller.request.OrderRequest;
import com.cherkas.vladimir.logisticstransportations.model.Cargo;
import com.cherkas.vladimir.logisticstransportations.repository.CargoRepository;
import com.cherkas.vladimir.logisticstransportations.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoRepository cargoRepository;
    private final OrderRepository orderRepository;

    public void saveCargo(OrderRequest request){
        var cargo = Cargo.builder()
                .height(request.getHeight())
                .length(request.getLength())
                .width(request.getWidth())
                .weight(request.getWeight())
                .additionalInfo(request.getInfo())
                .fragile(request.getFragile())
                .dangerous(request.getDangerous())
                .orderID(orderRepository.findOrderByOrderName(request.getName()).get().getId())
                .build();
        cargoRepository.save(cargo);
    }
}
