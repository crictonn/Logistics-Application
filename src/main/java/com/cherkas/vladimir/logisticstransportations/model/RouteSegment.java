package com.cherkas.vladimir.logisticstransportations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "route_segments")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RouteSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "segment_start_point", nullable = false)
    private String segmentStartPoint;

    @Column(name = "segment_end_point", nullable = false)
    private String segmentEndPoint;

    @Column(name = "transport_id")
    private Long transportID;

    @Column(name = "order_id")
    private Long orderID;
}
