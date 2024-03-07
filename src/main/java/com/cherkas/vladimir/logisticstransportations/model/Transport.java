package com.cherkas.vladimir.logisticstransportations.model;

import jakarta.persistence.*;
import lombok.Data;

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

    @Column(name = "capacity", nullable = false)
    private Double capacity;


}
