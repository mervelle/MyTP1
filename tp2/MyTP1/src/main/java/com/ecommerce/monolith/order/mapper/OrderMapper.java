package com.ecommerce.monolith.order.mapper;

import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    // Si l'entité et le DTO ont des noms différents, on utilise @Mapping
    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "totalAmount", target = "totalAmount")
    OrderDTO toDTO(Order order);
}
