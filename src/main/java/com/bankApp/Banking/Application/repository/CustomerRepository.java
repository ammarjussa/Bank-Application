package com.bankApp.Banking.Application.repository;

import com.bankApp.Banking.Application.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByEmailAndPassword(String email, String password);

    Optional <Customer> findByEmail(String email);
}
