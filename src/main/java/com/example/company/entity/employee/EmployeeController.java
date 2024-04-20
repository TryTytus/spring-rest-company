package com.example.company.entity.employee;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    @Autowired

    private EmployeeDao employeeDao;


    @GetMapping("/byOrderNum")
    public List<Object[]> byOrderNum()
    {
        return employeeDao.getEmployeeByOrderNum();
    }


    @GetMapping("/byProductNum")
    public List<Object[]> byProductNum()
    {
        return employeeDao.getEmployeeByProductsNum();
    }


}
