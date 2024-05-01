package com.cherkas.vladimir.logisticstransportations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_name", length = 100, nullable = false)
    private String orderName;

    @Column(name = "departure_point", length = 100, nullable = false)
    private String departurePoint;

    @Column(name = "destination_point", length = 100, nullable = false)
    private String destinationPoint;

    @Column(name = "route_length")
    private Double routeLength;

    @Column(name = "registration_date")
    private Date regDate;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "status")
    private String status;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "company_id")
    private Long companyID;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<Cargo> cargos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<RouteSegment> routeSegments = new ArrayList<>();
}
