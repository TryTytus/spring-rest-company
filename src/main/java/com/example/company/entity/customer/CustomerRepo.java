package com.example.company.entity.customer;


import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CustomerRepo extends
        CrudRepository<Customer, Long>,
        PagingAndSortingRepository<Customer, Long>
{
    Page<Customer> getAllByOrdersEmpty(Pageable pageable);
}
