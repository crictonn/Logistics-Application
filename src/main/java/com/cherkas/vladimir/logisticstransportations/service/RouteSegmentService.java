package com.cherkas.vladimir.logisticstransportations.service;

import com.cherkas.vladimir.logisticstransportations.controller.request.OrderRequest;
import com.cherkas.vladimir.logisticstransportations.model.RouteSegment;
import com.cherkas.vladimir.logisticstransportations.repository.OrderRepository;
import com.cherkas.vladimir.logisticstransportations.repository.RouteSegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteSegmentService {
    private final RouteSegmentRepository routeSegmentRepository;
    private final OrderRepository orderRepository;

    public void saveSegment(String startPoint, String endPoint, Long id){
        RouteSegment segment = RouteSegment.builder()
                .segmentStartPoint(startPoint)
                .segmentEndPoint(endPoint)
                .orderID(id)
                .build();
        routeSegmentRepository.save(segment);
    }

    public void parseRouteSegments(OrderRequest request){
        Long orderID = orderRepository.findOrderByOrderName(request.getName()).get().getId();

        if(request.getAdditionalPoint1().equals("") && request.getAdditionalPoint2().equals("")){
            saveSegment(request.getDeparturePoint(),
                    request.getDestinationPoint(),
                    orderID);
        }
        else if(request.getAdditionalPoint1().equals("")) {
            saveSegment(request.getDeparturePoint(),
                    request.getAdditionalPoint2(),
                    orderID);
            saveSegment(request.getAdditionalPoint2(),
                    request.getDestinationPoint(),
                    orderID);
        }
        else if(!request.getAdditionalPoint2().equals("")) {
            saveSegment(request.getDeparturePoint(),
                    request.getAdditionalPoint1(),
                    orderID);
            saveSegment(request.getAdditionalPoint1(),
                    request.getDestinationPoint(),
                    orderID);
        } else {
            saveSegment(request.getDeparturePoint(),
                    request.getAdditionalPoint1(),
                    orderID);
            saveSegment(request.getAdditionalPoint1(),
                    request.getAdditionalPoint2(),
                    orderID);
            saveSegment(request.getAdditionalPoint2(),
                    request.getDestinationPoint(),
                    orderID);
        }
    }
}

