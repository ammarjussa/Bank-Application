package com.bankApp.Banking.Application.controller;

import com.bankApp.Banking.Application.model.Customer;
import com.bankApp.Banking.Application.repository.CustomerRepository;
import com.bankApp.Banking.Application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @PostMapping
    public Long createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
}
