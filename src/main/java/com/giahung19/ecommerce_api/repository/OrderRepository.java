package com.giahung19.ecommerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.giahung19.ecommerce_api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}