package com.cherkas.vladimir.logisticstransportations.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Entity
@Table(name = "transport")
@Data
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 30, nullable = false)
    private String type;

    @Column(name = "speed")
    private Double averageTransportationSpeed;

    @Column(name = "cost", nullable = false)
    private Double costPerKM;

    @Column(name = "max_weight", nullable = false)
    private Double maxWeight;

    @Column(name = "cargo_compartment_height")
    private Double cargoCompartmentHeight;

    @Column(name = "cargo_compartment_width")
    private Double cargoCompartmentWidth;

    @Column(name = "cargo_compartment_length")
    private Double cargoCompartmentLength;

    @Column(name = "company_id")
    private Long companyID;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id")
    private ArrayList<RouteSegment> transportInvolved = new ArrayList<>();
}
