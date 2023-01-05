package com.bankApp.Banking.Application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "bankAccount")
@JsonInclude(JsonInclude.Include.NON_NULL)


public class BankAccount {
    @Id
    private Long id;
    private Customer customer;
    private String accountNumber;
    private double balance;
}
