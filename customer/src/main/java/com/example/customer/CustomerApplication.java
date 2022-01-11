package com.example.customer;

import com.example.customer.dto.CustomerRequestDTO;
import com.example.customer.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerService cs){
        return args -> {
            cs.save(new CustomerRequestDTO("Customer1", "c01@gmail.com"));
            cs.save(new CustomerRequestDTO("Customer2", "c01@gmail.com"));
            cs.update(new CustomerRequestDTO("Customer5", "c01@gmail.com"),"2");
            cs.listCustomer().forEach(customer->{
                System.out.println(customer);
            });
            ;
                    };
    }

}
