package com.ecommerce.monolith.order.service;

import com.ecommerce.monolith.customer.service.CustomerService;
import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.model.Order;
import com.ecommerce.monolith.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;


import com.ecommerce.monolith.order.mapper.OrderMapper; // Importez le nouveau mapper
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final OrderMapper orderMapper; // Injectez-le ici

    @Override
    public boolean validateAndCreateOrder(Long customerId, Double amount) {
        if (customerService.existsById(customerId)) {
            Order newOrder = new Order();
            newOrder.setCustomerId(customerId);
            newOrder.setTotalAmount(amount); // Utilise le paramètre reçu de Postman
            newOrder.setOrderDate(LocalDateTime.now());

            orderRepository.save(newOrder);
            return true;
        }
        return false;
    }
    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        // 1. Get entities from database
        List<Order> orders = orderRepository.findByCustomerId(customerId);

        // 2. Convert each Entity to a DTO using the mapper
        return orders.stream()
                .map(orderMapper::toDTO)
                .toList();
    }


}