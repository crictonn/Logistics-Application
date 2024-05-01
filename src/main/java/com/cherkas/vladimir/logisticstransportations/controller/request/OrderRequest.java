package com.cherkas.vladimir.logisticstransportations.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String username;
    private String departurePoint;
    private String destinationPoint;
    private String additionalPoint1;
    private String additionalPoint2;
    private Double length;
    private Double width;
    private Double height;
    private Double weight;
    private String info;
    private String name;
    private Boolean fragile;
    private Boolean dangerous;
    private Long transportType;
}
