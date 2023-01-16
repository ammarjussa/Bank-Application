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
@CrossOrigin
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
    public ResponseEntity<Object> createAccount(@RequestBody Map<String, String> request) {
        // Find the user's account
        String userId = request.get("userId");
        String type = request.get("accountType");
        Optional<Customer> customer = customerService.getCustomerById(userId);
        if (!customer.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        BankAccount bankAccount = new BankAccount(generateId(), customer.get(), generateAccountNumber(), type,0);

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

    @PostMapping("/loadBalance")
    public ResponseEntity<Object> loadBalance(@RequestBody Map<String, String> request) {
        double amount;
        try {
            amount = Double.parseDouble(request.get("balance"));
        } catch (NumberFormatException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid amount");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<BankAccount> account = bankAccountService.findByAccountNumber(request.get("accountNumber"));
        if (!account.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid account number");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        account.get().setBalance(account.get().getBalance() + amount);
        bankAccountService.createBankAccount(account.get());

        Map<String, String> response = new HashMap<>();
        response.put("AccountNumber",account.get().getAccountNumber());
        response.put("amount", String.valueOf(amount));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/getCustomerAccount")
    public ResponseEntity<Object> findCustomerAccount(@RequestBody Map<String, String> request) {
        Optional<BankAccount> account = bankAccountService.findAccountByCustomerUserId(request.get("userId"));
        if (!account.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "No such account");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("accountNumber", account.get().getAccountNumber());
            response.put("accountType", account.get().getAccountType());
            response.put("balance", String.valueOf(account.get().getBalance()));
            response.put("name", account.get().getCustomer().getName());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
