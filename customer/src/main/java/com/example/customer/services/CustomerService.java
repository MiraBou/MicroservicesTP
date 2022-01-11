package com.example.customer.services;


import com.example.customer.dto.CustomerRequestDTO;
import com.example.customer.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO,String id);
    CustomerResponseDTO getCustomerById(String id);
    List<CustomerResponseDTO> listCustomer();
}
