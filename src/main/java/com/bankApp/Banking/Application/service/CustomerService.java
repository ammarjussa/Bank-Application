package com.bankApp.Banking.Application.service;

import com.bankApp.Banking.Application.model.Customer;
import com.bankApp.Banking.Application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public Long save(Customer customer) {
        return customerRepository.save(customer).getUserId();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
