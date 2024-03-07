package com.cherkas.vladimir.logisticstransportations.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "companies")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;



    //Тут нужен список заказов, которые взяла компания и список транспортов, доступных компании
}
