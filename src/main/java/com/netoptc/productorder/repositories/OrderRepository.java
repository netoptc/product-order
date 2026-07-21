package com.netoptc.productorder.repositories;

import com.netoptc.productorder.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> { }
