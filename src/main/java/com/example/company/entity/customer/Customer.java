package com.example.company.entity.customer;


import com.example.company.entity.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private Character sex;

    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String streetNumber;
    @NotNull
    private String postCode;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Order> orders = new ArrayList<>();

}
