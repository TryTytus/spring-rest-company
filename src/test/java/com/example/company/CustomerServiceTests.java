package com.example.company;


import com.example.company.entity.customer.Customer;
import com.example.company.entity.customer.CustomerService;
import com.example.company.entity.order.Order;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootTest
public class CustomerServiceTests {

    @Autowired
    private CustomerService customerService;

//    @Test
//    public void saveCustomerWithOrders()
//    {
//        Faker faker = new Faker();
//
//
//
//        Collection<Order> orderCollection = new ArrayList<>();
//
//        for (int i = 0; i < 3; i++) {
//            orderCollection.add(Order.builder().build());
//        }
//
//        Customer customer = Customer.builder()
//                .firstName(faker.name().firstName())
//                .lastName(faker.name().lastName())
//                .email(faker.internet().emailAddress())
//                .phone().orders()
//
//    }


}
