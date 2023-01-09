package com.bankApp.Banking.Application.controller;

import com.bankApp.Banking.Application.model.Customer;
import com.bankApp.Banking.Application.repository.CustomerRepository;
import com.bankApp.Banking.Application.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody Customer customer) {
        try {
            customerService.save(customer);

            Map<String, String> response = new HashMap<>();
            response.put("name", customer.getName());
            response.put("address", customer.getAddress());
            response.put("phone", customer.getPhoneNumber());
            response.put("email", customer.getEmail());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "An error occurred while creating the account");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("email");
        String password = request.get("password");

        Customer customer = customerService.login(email, password);
        if (customer == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        session.setAttribute("email", email);

        Map<String, String> response = new HashMap<>();
        response.put("name", customer.getName());
        response.put("address", customer.getAddress());
        response.put("phone", customer.getPhoneNumber());
        response.put("email", customer.getEmail());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        if (session.getAttribute("email") == null) {
            return new ResponseEntity<>("Not logged in", HttpStatus.BAD_REQUEST);
        } else {
            session.invalidate();
            return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
        }
    }
}

/*
* Signup, Login and Logout APIs working.
* Have to check bank Account creation
* Figure out how to add bank account details into the customer.
* Check transaction api and test it.
* Other APIs: getTransactionDetails, getBankAccountDetails, getCashATM etc.
* */


