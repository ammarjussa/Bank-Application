package com.bankApp.Banking.Application.controller;

import com.bankApp.Banking.Application.model.BankAccount;
import com.bankApp.Banking.Application.model.Customer;
import com.bankApp.Banking.Application.model.Transaction;
import com.bankApp.Banking.Application.model.TransactionRequest;
import com.bankApp.Banking.Application.service.BankAccountService;
import com.bankApp.Banking.Application.service.CustomerService;
import com.bankApp.Banking.Application.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<Transaction> getAllTransactions(BankAccount account) {
        return transactionService.getAllTransactionsOfAccount(account);
    }

    @PostMapping("/createTransaction")
    public ResponseEntity<Object> createTransaction(@RequestBody Map<String, String> request, HttpSession session) {

        String receiverAccountNumber = request.get("receiverAccountNumber");
        if (receiverAccountNumber == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "You must provide the account number of the receiver");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<BankAccount> receiverAccount = bankAccountService.findByAccountNumber(receiverAccountNumber);
        if (receiverAccount == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid account number");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String senderAccountNumber = request.get("senderAccountNumber");
        if (senderAccountNumber == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "You must provide the account number of the sender");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<BankAccount> senderAccount = bankAccountService.findByAccountNumber(receiverAccountNumber);
        if (senderAccount == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid account number");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


        String amountString = request.get("amount");
        if (amountString == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "You must provide the amount of the transaction");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        double amount;
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid amount");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (senderAccount.get().getBalance() < amount) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "You do not have sufficient balance to make this transaction");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setId(generateId());
        transaction.setSourceAccount(senderAccount.get());
        transaction.setDestinationAccount(receiverAccount.get());
        transaction.setAmount(amount);
        transaction.setDescription(request.get("description"));
        transaction.setDate(new Date());


        // Update the balance of the sender and receiver
        senderAccount.get().setBalance(senderAccount.get().getBalance() - amount);
        receiverAccount.get().setBalance(receiverAccount.get().getBalance() + amount);

        // Save the updated bank accounts to the database
        bankAccountService.createBankAccount(senderAccount.get());
        bankAccountService.createBankAccount(receiverAccount.get());

        // Save the transaction to the database
        transactionService.createTransaction(transaction);

        // Create a response object
        Map<String, String> response = new HashMap<>();
        response.put("id", transaction.getId());
        response.put("senderAccountNumber", transaction.getSourceAccount().getAccountNumber());
        response.put("receiverAccountNumber", transaction.getDestinationAccount().getAccountNumber());
        response.put("amount", String.valueOf(transaction.getAmount()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }
}
