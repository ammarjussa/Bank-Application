package com.bankApp.Banking.Application.repository;

import com.bankApp.Banking.Application.model.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankAccountRepository extends MongoRepository<BankAccount, String> {
}
