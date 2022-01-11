package com.example.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor
@ToString
public class CustomerResponseDTO {
    private String id;
    private String name;
    private String email;
}
