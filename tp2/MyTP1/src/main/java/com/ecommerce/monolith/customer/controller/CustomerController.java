package com.ecommerce.monolith.customer.controller;

import com.ecommerce.monolith.customer.model.Customer;
import com.ecommerce.monolith.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerRepository repository;

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return repository.save(customer);
    }
}