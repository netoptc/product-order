package com.netoptc.productorder.repositories;

import com.netoptc.productorder.entities.OrderItem;
import com.netoptc.productorder.entities.OrderItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemKey> { }