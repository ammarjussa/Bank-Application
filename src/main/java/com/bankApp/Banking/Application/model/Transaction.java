package com.bankApp.Banking.Application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "transactions")
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)


public class Transaction {
    @Id
    private String id;

    private BankAccount sourceAccount;

    private BankAccount destinationAccount;

    private double amount;
    private Date date;
    private String description;

    public Transaction(BankAccount sourceAccount, BankAccount destinationAccount, double amount, String description) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.description = description;
    }

}
