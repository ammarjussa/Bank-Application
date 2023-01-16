package com.bankApp.Banking.Application.service;

import com.bankApp.Banking.Application.model.BankAccount;
import com.bankApp.Banking.Application.model.Transaction;
import com.bankApp.Banking.Application.repository.BankAccountRepository;
import com.bankApp.Banking.Application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<Transaction> getAllTransactionsOfAccount(BankAccount account) {
        return transactionRepository.findAllBySourceAccountOrDestinationAccountOrderByDateDesc(account, account);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
