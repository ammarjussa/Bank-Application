package com.bankApp.Banking.Application.repository;

import com.bankApp.Banking.Application.model.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BankAccountRepository extends MongoRepository<BankAccount, String> {
    Optional<BankAccount> findBankAccountById(Long id);

    Optional<BankAccount> findBankAccountByAccountNumber(String accountNumber);
}
