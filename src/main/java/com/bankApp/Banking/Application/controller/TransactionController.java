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
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public Transaction createTransaction(TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

//    @PostMapping("/createTransaction")
//    public ResponseEntity<Object> createTransaction(@RequestBody Map<String, String> request, HttpSession session) {
//        String id = (String) session.getAttribute("userId");
//        if (id == null) {
//            Map<String, String> response = new HashMap<>();
//            response.put("error", "You must be logged in to create a transaction");
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//        }
//
//        Optional<Customer> customer =  customerService.getCustomerById(id);
//        if (customer == null) {
//            Map<String, String> response = new HashMap<>();
//            response.put("error", "Invalid email or password");
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//        }
//
//        String receiverAccountNumber = request.get("receiverAccountNumber");
//        if (receiverAccountNumber == null) {
//            Map<String, String> response = new HashMap<>();
//            response.put("error", "You must provide the account number of the receiver");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        Optional<BankAccount> receiverAccount = bankAccountService.findByAccountNumber(receiverAccountNumber);
//        if (receiverAccount == null) {
//            Map<String, String> response = new HashMap<>();
//            response.put("error", "Invalid account number");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        String amountString = request.get("amount");
//        if (amountString == null) {
//            Map<String, String> response = new HashMap<>();
//            response.put("error", "You must provide the amount of the transaction");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//        int amount;
//        try {
//            amount = Integer.parseInt(amountString);
//        } catch (NumberFormatException e) {
//            Map<String, String> response = new HashMap<>();
//            response.put("error", "Invalid amount");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        if (customer.getBankAccount().getBalance() < amount) {
//            Map<String, String> response = new HashMap<>();
//            response.put("error", "You do not have sufficient balance to make this transaction");
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//
//        // Create a new transaction
//        Transaction transaction = new Transaction();
//        transaction.setId(generateId());
//        transaction.setSourceAccount(customer.getBankAccount());
//        transaction.setDestinationAccount(receiverAccount.get());
//        transaction.setAmount(amount);
//
//        // Update the balance of the sender and receiver
//        customer.getBankAccount().setBalance(customer.getBankAccount().getBalance() - amount);
//        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
//
//        // Save the updated bank accounts to the database
//        bankAccountService.createBankAccount(customer.getBankAccount());
//        bankAccountService.createBankAccount(receiverAccount);
//
//        // Save the transaction to the database
//        transactionRepository.save(transaction);
//
//        // Create a response object
//        Map<String, String> response = new HashMap<>();
//        response.put("id", transaction.getId());
//        response.put("senderAccountNumber", transaction.getSourceAccount().getAccountNumber());
//        response.put("receiverAccountNumber", transaction.getDestinationAccount().getAccountNumber());
//        response.put("amount", String.valueOf(transaction.getAmount()));
//
//        // Return the response as a JSON object
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    private String generateId() {
//        // Generate a random ID
//        return UUID.randomUUID().toString();
//    }
}
