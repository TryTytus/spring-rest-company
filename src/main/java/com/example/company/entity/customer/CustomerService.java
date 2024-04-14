package com.example.company.entity.customer;

import com.example.company.entity.order.Order;
import com.example.company.entity.order.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepo customerRepo;
    private OrderRepo orderRepo;


//    public Customer saveCustomerWithOrders(Customer customer, Collection<Order> orderList)
//    {
//        customer.orders.addAll(orderList);
//        return customerRepo.save(customer);
//    }


}
