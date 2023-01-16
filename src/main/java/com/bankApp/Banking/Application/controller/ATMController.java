package com.bankApp.Banking.Application.controller;

import com.bankApp.Banking.Application.model.BankAccount;
import com.bankApp.Banking.Application.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ATMController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/atm/transaction")
    public String doTransaction(@ModelAttribute("transactionType") String transactionType,
                                @ModelAttribute("name") String name,
                                @ModelAttribute("account") String account,
                                @ModelAttribute("amount") double amount,
                                Model model) {

        String status = "";
        Optional<BankAccount> bankAccount = bankAccountService.findByAccountNumber(account);
        if (!bankAccount.isPresent()) {
            status = "FAILURE";
        } else {
            System.out.println(amount);
            if(Objects.equals(transactionType, "DEPOSIT") || Objects.equals(transactionType, "deposit")) {
                bankAccount.get().setBalance(bankAccount.get().getBalance() + amount);
                bankAccountService.createBankAccount(bankAccount.get());
                status = "SUCCESS";
            } else if(Objects.equals(transactionType, "WITHDRAW") || Objects.equals(transactionType, "withdraw")) {
                bankAccount.get().setBalance(bankAccount.get().getBalance() - amount);
                bankAccountService.createBankAccount(bankAccount.get());
                status = "SUCCESS";
            } else {
                status = "FAILURE";
            }
        }
        model.addAttribute("transactionStatus", status);
        return "atm";
    }

    @GetMapping("/atm")
    public String getATMView() {
        return "atm";
    }
}
