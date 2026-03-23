package com.ecommerce.monolith.customer.service;

import com.ecommerce.monolith.customer.dto.CustomerDTO;

public interface CustomerService {
    boolean existsById(Long id); // Requirement Step 2
    CustomerDTO getCustomerById(Long id);
}