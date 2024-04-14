package com.example.company.faker;

import com.example.company.entity.company.Company;
import com.example.company.entity.company.CompanyRepo;
import com.example.company.entity.customer.Customer;
import com.example.company.entity.customer.CustomerRepo;
import com.example.company.entity.employee.Employee;
import com.example.company.entity.employee.EmployeeRepo;
import com.example.company.entity.order.Order;
import com.example.company.entity.order.OrderRepo;
import com.example.company.entity.product.Product;
import com.example.company.entity.product.ProductRepo;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;


@AllArgsConstructor
@Component
public class FakeDataOnStartup implements ApplicationRunner {

    private EmployeeRepo employeeRepo;
    private CompanyRepo companyRepo;
    private ProductRepo productRepo;
    private CustomerRepo customerRepo;
    private OrderRepo orderRepo;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        Faker faker = new Faker();


        for (int i = 0; i < 30; i++) {

        employeeRepo.save(
                Employee.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .address(faker.address().streetAddress())
                        .profession(faker.company().profession())
                        .number(faker.phoneNumber().phoneNumber())

                        .build()
                );

        }


        for (int i = 0; i < 20; i++) {
            companyRepo.save(
                    Company.builder()
                            .name(faker.company().name())
                            .industry(faker.company().industry())
                            .country(faker.address().country())
                            .size(faker.number().numberBetween(20, 150))
                            .build()
            );
        }


        for (int i = 0; i < 100; i++) {
            productRepo.save(
                    Product.builder()
                            .name(faker.commerce().productName())
                            .color(faker.color().name())
                            .price(faker.number().numberBetween(1_0000, 1000_000))
                            .count(faker.number().numberBetween(5, 200))
                            .build()
            );
        }

        for (int i = 0; i < 20; i++) {
            List<Order> orderList = new ArrayList<>();

            for (int j = 0; j < faker.number().numberBetween(1, 3); j++) {
                orderList.add(Order.builder()
                        .quantity(faker.number().numberBetween(1, 20))
                        .status(faker.options().option("READY", "PROCESSED", "CONFIRMED"))
                        .description(String.join(" ", faker.lorem().words(4)))
                        .discount(faker.number().numberBetween(10, 30))
                        .build()
                );
            }


            Customer customer = Customer.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .city(faker.address().city())
                    .phone(faker.phoneNumber().phoneNumber())
                    .sex((faker.bool().bool()) ? 'M' : 'F')
                    .postCode(faker.address().zipCode())
                    .street(faker.address().streetName())
                    .streetNumber(faker.address().streetAddressNumber())
                    .build();

            orderList.forEach(
                    order -> {order.setCustomer(customer);}
                    );

            customer.setOrders(orderList);

            customerRepo.save(customer);

//           List<Order> orders = new ArrayList<>();
//           orderRepo.findAll().forEach(orders::add);
//
//
//            Iterable<Employee> employees = employeeRepo.findAll();
//
//            employees.forEach( empl ->
//                    empl.setOrders(new HashSet<>(){{
//                            add(orders.get(faker.number().numberBetween(0, orders.size() - 1)));
//                            add(orders.get(faker.number().numberBetween(0, orders.size() - 1)));
//                    }}));
//
//            employeeRepo.saveAll(employees);
        }




        


    }
}
