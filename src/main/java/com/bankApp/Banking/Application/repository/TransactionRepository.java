package com.bankApp.Banking.Application.repository;

import com.bankApp.Banking.Application.model.BankAccount;
import com.bankApp.Banking.Application.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findAllBySourceAccountAndDestinationAccount(BankAccount account);
}

