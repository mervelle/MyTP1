package com.ecommerce.monolith.order.service;

import com.ecommerce.monolith.order.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    boolean validateAndCreateOrder(Long customerId, Double amount); // Ajoute Double amount ici
    List<OrderDTO> getOrdersByCustomerId(Long customerId);
}