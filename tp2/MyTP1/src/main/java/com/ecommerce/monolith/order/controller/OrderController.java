package com.ecommerce.monolith.order.controller;

import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders") // This must match your Postman URL
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public boolean createOrder(@RequestBody OrderDTO request) {
        // On passe le customerId ET le totalAmount au service
        return orderService.validateAndCreateOrder(
                request.getCustomerId(),
                request.getTotalAmount()
        );
    }
    // Dans com.ecommerce.monolith.order.controller.OrderController

    @GetMapping("/customer/{customerId}") // Assure-toi que c'est GET
    public List<OrderDTO> getHistory(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }
}
