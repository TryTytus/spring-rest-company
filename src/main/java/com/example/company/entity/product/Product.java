package com.example.company.entity.product;

import com.example.company.entity.order.Order;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    String name;
    @Nullable
    String color;
    @NotNull
    Integer price;
    @NotNull
    Integer count;


    @OneToMany(mappedBy = "product")
    private Set<Order> orders = new HashSet<>();

}
