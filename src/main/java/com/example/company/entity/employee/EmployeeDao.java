package com.example.company.entity.employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import lombok.AllArgsConstructor;
import com.example.company.entity.order.Order;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class EmployeeDao {

    private EntityManager em;




    List<Object[]> getEmployeeByOrderNum() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Employee> root = cq.from(Employee.class);

        cq.multiselect(
                root.get("id"),
                root.get("firstName"),
                cb.count(root.join("orders", JoinType.LEFT))
        );

        cq.groupBy(root.get("id"), root.get("firstName"));
        cq.orderBy(cb.asc(cb.count(root.join("orders", JoinType.LEFT))));

        return em.createQuery(cq).getResultList();
    }


    List<Object[]> getEmployeeByProductsNum() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Employee> root = cq.from(Employee.class);
        Join<Employee, Order> join = root.join("orders", JoinType.LEFT);

        cq.multiselect(
                root.get("id"),
                root.get("firstName"),
                cb.sum(join.get("quantity"))
        );

        cq.groupBy(root.get("id"), root.get("firstName"));
        cq.orderBy(cb.desc(cb.sum(join.get("quantity"))));

        return em.createQuery(cq).getResultList();
    }



}


