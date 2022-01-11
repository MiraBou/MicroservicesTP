package com.example.customer.dto;

import lombok.*;

@Data
@RequiredArgsConstructor

public class CustomerRequestDTO {
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String email;
}
