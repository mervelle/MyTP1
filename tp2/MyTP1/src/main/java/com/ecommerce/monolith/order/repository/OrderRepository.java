package com.ecommerce.monolith.order.repository;

import com.ecommerce.monolith.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // This allows the service to find orders by the customer ID field
    List<Order> findByCustomerId(Long customerId);
}

