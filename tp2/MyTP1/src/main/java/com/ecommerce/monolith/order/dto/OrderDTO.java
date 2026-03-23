package com.ecommerce.monolith.order.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long customerId;
    private Double totalAmount;
}