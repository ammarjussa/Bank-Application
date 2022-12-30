package com.bankApp.Banking.Application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Customer {
    @Id
    private Long userId;
    private String name;
    private String address;
    private String phoneNumber;
}
