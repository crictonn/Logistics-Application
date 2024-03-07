package com.cherkas.vladimir.logisticstransportations.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 30, nullable = false)
    private String first_name;

    @Column(name = "second_name", length = 30)
    private String second_name;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name ="email", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name ="password", length = 100, nullable = false)
    private String password;

    @Column(name = "role", length = 10, nullable = false)
    private String role;

    //Дописать лист или сет заказов конкретного пользователя
    @OneToMany(mappedBy = user)
}
