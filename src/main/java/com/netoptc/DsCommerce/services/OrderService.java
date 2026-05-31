package com.netoptc.DsCommerce.services;


import com.netoptc.DsCommerce.dtos.OrderDto;
import com.netoptc.DsCommerce.dtos.OrderItemDto;
import com.netoptc.DsCommerce.entities.Order;
import com.netoptc.DsCommerce.entities.OrderItem;
import com.netoptc.DsCommerce.entities.Product;
import com.netoptc.DsCommerce.entities.User;
import com.netoptc.DsCommerce.enums.OrderStatus;
import com.netoptc.DsCommerce.exceptions.ResourceNotFoundException;
import com.netoptc.DsCommerce.repositories.OrderItemRepository;
import com.netoptc.DsCommerce.repositories.OrderRepository;
import com.netoptc.DsCommerce.repositories.ProductRepository;
import com.netoptc.DsCommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDto findById(Long id) {
        Order order =  orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDto(order);
    }

    @Transactional()
    public OrderDto insert(OrderDto dto) {
        Order newOrder = new Order();

        User client = userRepository.findById(1L).orElseThrow(ResourceNotFoundException::new);

        newOrder.setMoment(Instant.now());
        newOrder.setClient(client);
        newOrder.setStatus(OrderStatus.WAITING_PAYMENT);

        Set<OrderItem> items = new HashSet<>();

        for(OrderItemDto itemDto : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem newOrderItem = new OrderItem(newOrder, product, itemDto.getQuantity(), product.getPrice());
            items.add(newOrderItem);
        }

        newOrder.setOrderItems(items);

        orderRepository.save(newOrder);
        orderItemRepository.saveAll(items);

        return new OrderDto(newOrder);
    }
}
