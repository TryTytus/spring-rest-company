package com.example.company.entity.order;

import com.example.company.entity.customer.Customer;
import com.example.company.entity.employee.Employee;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity // order is reserved word in SQL !!!
@Table(name = "_order_")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Min(value = 1)
    @Max(value = 20)
    private Integer quantity;

    @NotNull
    @Size(max = 20)
    private String status;

    @Nullable
    private String description;

    @Min(value = 1)
    @Max(value = 30)
    @Nullable
    private Integer discount;


    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_order")
    )
    private Customer customer;


    @ManyToMany(mappedBy = "orders")
    private Set<Employee> employees;

}
