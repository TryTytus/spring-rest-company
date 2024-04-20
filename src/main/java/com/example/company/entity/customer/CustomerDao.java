package com.example.company.entity.customer;


import com.example.company.entity.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor

public class CustomerDao {
    private EntityManager em;

    /**
     * Find customers who have placed orders with a quantity
     * greater than the average order quantity
     */

    public List<Customer> getGreaterThanAvg()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);


        Root<Customer> root = query.from(Customer.class);
        Join<Customer, Order> join = root.join("orders", JoinType.LEFT);


        Subquery<Double> subQuery = query.subquery(Double.class);
        Root<Order> sbRoot = subQuery.from(Order.class);

        subQuery.select(cb.avg(sbRoot.get("quantity")));

        query.where(cb.greaterThan(join.get("quantity"), subQuery));
        query.orderBy(cb.asc(root.get("id")));

        return em.createQuery(query).getResultList();
    }

}
