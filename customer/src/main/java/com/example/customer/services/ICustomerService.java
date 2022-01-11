package com.example.customer.services;

import com.example.customer.dto.CustomerRequestDTO;
import com.example.customer.dto.CustomerResponseDTO;
import com.example.customer.entities.Customer;
import com.example.customer.mappers.CustomerMapper;
import com.example.customer.repositories.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
@Transactional
public class ICustomerService implements CustomerService{
@Autowired
    private CustomerRepo customerRepo;
@Autowired
    private CustomerMapper customerMap;


    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMap.customerRequestDtoTOCustomer(customerRequestDTO);
        Customer savedCustomer = customerRepo.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMap.customerToCustomerResponseDTO(savedCustomer);
        return customerResponseDTO;

    }

    @Override
    public CustomerResponseDTO getCustomerById(String id) {
        Customer customer = customerRepo.findById(id).get();
        CustomerResponseDTO customerResponseDTO = customerMap.customerToCustomerResponseDTO(customer);
        return customerResponseDTO;
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO,String id) {
        Customer customer = customerRepo.findById(id).get();
        customerMap.updateCustomerFromDto(customerRequestDTO,customer);
        Customer updatedCustomer= customerRepo.save(customer);
        CustomerResponseDTO customerResponseDTO = customerMap.customerToCustomerResponseDTO(updatedCustomer);
        return customerResponseDTO;
    }


    @Override
    public List<CustomerResponseDTO> listCustomer() {
        List <Customer> customerList = customerRepo.findAll();
        List<CustomerResponseDTO> responseList=new ArrayList<>();

        for(int i=0;i<customerList.size();i++){
            CustomerResponseDTO responseDTO = customerMap.customerToCustomerResponseDTO(customerList.get(i));
            responseList.add(responseDTO);
        }
        return responseList;
    }


}
