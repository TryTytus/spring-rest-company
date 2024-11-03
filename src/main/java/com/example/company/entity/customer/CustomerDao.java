package com.example.company.entity.customer;


import com.example.company.entity.order.Order;
import com.example.company.entity.product.Product;
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
        Join<Customer, Order> join = root.join("orders", JoinType.INNER);


        Subquery<Double> subQuery = query.subquery(Double.class);
        Root<Order> sbRoot = subQuery.from(Order.class);

        subQuery.select(cb.avg(sbRoot.get("quantity")));

        query.select(root);

        query.where(cb.greaterThan(join.get("quantity"), subQuery));
        query.orderBy(cb.asc(root.get("id")));

        return em.createQuery(query).getResultList();
    }

    public List<Customer> pipa()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);

        Root<Customer> root = query.from(Customer.class);
        Fetch<Customer, Order> fetch = root.fetch("orders", JoinType.LEFT);

        query.select(root);

        return em.createQuery(query).getResultList();
    }




    public List<Object[]> getWhoSpentTheMost()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);

        Root<Customer> customerRoot = q.from(Customer.class);
        Join<Customer, Order> joinOrder =  customerRoot.join("orders", JoinType.LEFT);
        Join<Order, Product> joinProduct = joinOrder.join("product", JoinType.INNER);



        q.multiselect(
                customerRoot.get("firstName"),
                customerRoot.get("lastName"),
                cb.sum(
                        cb.prod(joinOrder.get("quantity"), joinProduct.get("price"))
                ).alias("totalSpent")
        );

        q.groupBy(customerRoot.get("firstName"), customerRoot.get("lastName"));
        q.orderBy(cb.desc(cb.literal("totalSpent")));


        return em.createQuery(q).setMaxResults(10).getResultList();
    }




}
