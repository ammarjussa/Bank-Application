package com.bankApp.Banking.Application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class TransactionRequest {
    private Long sourceAccountId;
    private Long destinationAccountId;
    private double amount;
    private String description;
}
