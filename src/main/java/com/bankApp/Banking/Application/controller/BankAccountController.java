package com.bankApp.Banking.Application.controller;

import com.bankApp.Banking.Application.model.BankAccount;
import com.bankApp.Banking.Application.model.Customer;
import com.bankApp.Banking.Application.repository.BankAccountRepository;
import com.bankApp.Banking.Application.repository.CustomerRepository;
import com.bankApp.Banking.Application.service.BankAccountService;
import com.bankApp.Banking.Application.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")

public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllAccounts();
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Object> createAccount(HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "You must be logged in to create a bank account");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        // Find the user's account
        Optional<Customer> customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        BankAccount bankAccount = new BankAccount(generateId(), customer.get(), generateAccountNumber(), "savings",0);

        bankAccountService.createBankAccount(bankAccount);

        Map<String, String> response = new HashMap<>();
        response.put("accountNumber", bankAccount.getAccountNumber());
        response.put("balance", String.valueOf(bankAccount.getBalance()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String generateAccountNumber() {
        return String.valueOf(new Random().nextInt(100000000));
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

}
