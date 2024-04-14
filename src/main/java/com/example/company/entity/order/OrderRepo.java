package com.example.company.entity.order;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface OrderRepo extends
        CrudRepository<Order, Long>,
        PagingAndSortingRepository<Order, Long>
{

////    @Modifying
//    @RestResource(exported = false)
////    @Transactional
//    @Query(value =
//            "TRUNCATE TABLE _order_; alter table _order_  drop foreign key customer_order; ",
//            nativeQuery = true)
//    void truncate1();
//
//    @RestResource(exported = false)
//    @Query(value =
//            "TRUNCATE TABLE customer;  alter table if exists _order_ add constraint customer_order foreign key (customer_id) references customer (id);",
//            nativeQuery = true)
//    void truncate2();



}
