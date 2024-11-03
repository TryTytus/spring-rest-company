package com.example.company.entity.customer;



import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface CustomerRepo extends
        CrudRepository<Customer, Long>,
        PagingAndSortingRepository<Customer, Long>
{

    @EntityGraph("Customer.orders")
    public Object getAllById(Long id);

}
