package com.bankApp.Banking.Application.service;

import com.bankApp.Banking.Application.model.Customer;
import com.bankApp.Banking.Application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public String save(@RequestBody Customer customer) {
        return customerRepository.save(customer).getUserId();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer login(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }
}
