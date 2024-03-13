package com.cherkas.vladimir.logisticstransportations.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "route_segments")
@Data
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
}
