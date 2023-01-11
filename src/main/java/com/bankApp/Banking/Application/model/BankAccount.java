package com.bankApp.Banking.Application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "bankAccount")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor


public class BankAccount {
    @Id
    private String id;
    private Customer customer;
    private String accountNumber;
    private String accountType;
    private double balance;
}
