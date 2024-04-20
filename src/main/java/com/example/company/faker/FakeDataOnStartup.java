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
import jakarta.el.LambdaExpression;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;


@AllArgsConstructor
@Component
public class FakeDataOnStartup implements ApplicationRunner {

    private EmployeeRepo employeeRepo;
    private CompanyRepo companyRepo;
    private ProductRepo productRepo;
    private CustomerRepo customerRepo;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Faker faker = new Faker();

        List<Company> companies = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            companies.add(
                    Company.builder()
                            .name(faker.company().name())
                            .industry(faker.company().industry())
                            .country(faker.address().country())
                            .size(faker.number().numberBetween(20, 150))
                            .build()
            );
        }

        companyRepo.saveAll(companies);

        ArrayList<Customer> customers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            customers.add(Customer.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .city(faker.address().city())
                    .phone(faker.phoneNumber().phoneNumber())
                    .sex((faker.bool().bool()) ? 'M' : 'F')
                    .postCode(faker.address().zipCode())
                    .street(faker.address().streetName())
                    .streetNumber(faker.address().streetAddressNumber())
                    .build());
        }

        Iterable<Customer> savedCustomers = customerRepo.saveAll(customers);
        Iterator<Customer> customerIterator = savedCustomers.iterator();


        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            products.add(
                    Product.builder()
                            .name(faker.commerce().productName())
                            .color(faker.color().name())
                            .price(faker.number().numberBetween(1_0000, 1000_000))
                            .count(faker.number().numberBetween(5, 200))
                            .build()
            );
        }

        Iterable<Product> productIterable =  productRepo.saveAll(products);
        Iterator<Product> productIterator = productIterable.iterator();



        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 30; i++) {

            employees.add(
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




        for (Employee employee : employees) {
            employee.setOrders(new HashSet<>());


            for (int i = 0; i < faker.number().numberBetween(1, 3); i++) {
                if (!customerIterator.hasNext())
                    customerIterator = savedCustomers.iterator();
                if (!productIterator.hasNext())
                    productIterator = productIterable.iterator();

                employee.getOrders().add(
                        Order.builder()
                                .quantity(faker.number().numberBetween(1, 20))
                                .status(faker.options().option("READY", "PROCESSED", "CONFIRMED"))
                                .description(String.join(" ", faker.lorem().words(4)))
                                .discount(faker.number().numberBetween(10, 30))
                                .customer(customerIterator.next())
                                .product(productIterator.next())
                                .build()
                );
            }

        }
        employeeRepo.saveAll(employees);


    }
}
