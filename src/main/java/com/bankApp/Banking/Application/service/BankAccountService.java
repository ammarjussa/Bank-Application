package com.bankApp.Banking.Application.service;

import com.bankApp.Banking.Application.model.BankAccount;
import com.bankApp.Banking.Application.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public Optional<BankAccount> findByAccountNumber(String accountNumber) {
        return bankAccountRepository.findBankAccountByAccountNumber(accountNumber);
    }
}
