package com.bankApp.Banking.Application.repository;

import com.bankApp.Banking.Application.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
