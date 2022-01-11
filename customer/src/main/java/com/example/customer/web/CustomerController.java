package com.example.customer.web;


import com.example.customer.dto.CustomerRequestDTO;
import com.example.customer.dto.CustomerResponseDTO;
import com.example.customer.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping(path = "/customersAdd")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO ResponseDTO = customerService.save(customerRequestDTO);
        return ResponseDTO;
    }
    @GetMapping(path = "/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id){
        CustomerResponseDTO ResponseDTO = customerService.getCustomerById(id);
        System.out.println(ResponseDTO);
        return ResponseDTO ;
    }

    @PostMapping(path = "/customerUpdate/{id}")
    public CustomerResponseDTO update(@RequestBody CustomerRequestDTO customerRequestDTO,@PathVariable String id){
        CustomerResponseDTO ResponseDTO = customerService.update(customerRequestDTO,id);
        return ResponseDTO ;
    }

    @GetMapping(path = "/customers")
    public List<CustomerResponseDTO> getAllCustomers(){
        List<CustomerResponseDTO> ResponseDTO = customerService.listCustomer();
        return ResponseDTO ;
    }
}
