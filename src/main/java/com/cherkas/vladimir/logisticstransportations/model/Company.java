package com.cherkas.vladimir.logisticstransportations.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Entity
@Table(name = "companies")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 75)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "company_email", length = 50)
    private String email;

    @Column(name = "company_phone", length = 75)
    private String phone;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private ArrayList<User> employees = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private ArrayList<Transport> availableTransports = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private ArrayList<Order> orders = new ArrayList<>();
}
