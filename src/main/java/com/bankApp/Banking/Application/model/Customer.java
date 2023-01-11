package com.bankApp.Banking.Application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor

public class Customer {
    @Id
    private String userId;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
}

