package com.example.company.entity.customer;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@AllArgsConstructor
public class CustomerController {

    CustomerDao customerDao;

    @GetMapping("/getGreaterThanAvg")
    @ResponseBody
    public List<Customer> getGreaterThanAvg()
    {

        return customerDao.pipa();
    }


    @GetMapping("/getWhoSpentTheMost")
    @ResponseBody
    public List<Object[]> getWhoSpentTheMost()
    {
        return customerDao.getWhoSpentTheMost();
    }


}
