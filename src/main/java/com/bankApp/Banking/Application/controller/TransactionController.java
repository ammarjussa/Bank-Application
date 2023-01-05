package com.bankApp.Banking.Application.controller;

import com.bankApp.Banking.Application.model.Transaction;
import com.bankApp.Banking.Application.model.TransactionRequest;
import com.bankApp.Banking.Application.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public Transaction createTransaction(TransactionRequest request) {
        return transactionService.createTransaction(request);
    }
}
