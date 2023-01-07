package com.bankApp.Banking.Application.controller;

import com.bankApp.Banking.Application.model.Customer;
import com.bankApp.Banking.Application.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/account")
public class CreateAccountController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/signup")
    public String createAccountPage() {
        return "createAccount";
    }

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    @PostMapping("/signup")
    public String createCustomer(@ModelAttribute Customer customer) {
        String userId = customerService.save(customer);
        System.out.print(userId);
        return "redirect:/home";
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Customer customer = customerService.login(email, password);
        if (customer != null) {
            session.setAttribute("customer", customer);
            return "redirect:/";
        } else {
            return "login";
        }
    }
}
