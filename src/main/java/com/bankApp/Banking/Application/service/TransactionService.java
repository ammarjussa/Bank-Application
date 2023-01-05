package com.bankApp.Banking.Application.service;

import com.bankApp.Banking.Application.model.BankAccount;
import com.bankApp.Banking.Application.model.Transaction;
import com.bankApp.Banking.Application.model.TransactionRequest;
import com.bankApp.Banking.Application.repository.BankAccountRepository;
import com.bankApp.Banking.Application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(TransactionRequest request) {
        BankAccount sourceAccount = bankAccountRepository.findBankAccountById(request.getSourceAccountId()).get();
        BankAccount destinationAccount = bankAccountRepository.findBankAccountById(request.getDestinationAccountId()).get();
        double amount = request.getAmount();
        String description = request.getDescription();

        Transaction transaction = new Transaction(sourceAccount, destinationAccount, amount, description);
        return transactionRepository.save(transaction);
    }


}
