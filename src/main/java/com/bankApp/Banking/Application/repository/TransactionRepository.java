package com.bankApp.Banking.Application.repository;

import com.bankApp.Banking.Application.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}

