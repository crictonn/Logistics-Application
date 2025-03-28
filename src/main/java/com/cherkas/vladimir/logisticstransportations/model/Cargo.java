package com.cherkas.vladimir.logisticstransportations.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cargos")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "height")
    private Double height;

    @Column(name = "width")
    private Double width;

    @Column(name = "length")
    private Double length;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "fragile")
    private Boolean fragile;

    @Column(name = "dangerous")
    private Boolean dangerous;

    @Column(name = "additional_info", length = 100)
    private String additionalInfo;

    @Column(name = "order_id")
    private Long orderID;
}
