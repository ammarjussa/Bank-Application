package com.bankApp.Banking.Application.service;

import com.bankApp.Banking.Application.model.BankAccount;
import com.bankApp.Banking.Application.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public void transaction(String request) { // parameter should have src and dst bank ids with balance to transfer
//        BankAccount sourceAccount = bankAccountRepository.findById(request.getSourceAccountId()).get();
//        BankAccount destinationAccount = bankAccountRepository.findById(request.getDestinationAccountId()).get();
//        double amount = request.getAmount();
//
//        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
//        destinationAccount.setBalance(destinationAccount.getBalance() + amount);
//
//        bankAccountRepository.save(sourceAccount);
//        bankAccountRepository.save(destinationAccount);
    }
}
