package com.netoptc.DsCommerce.controllers;


import com.netoptc.DsCommerce.dtos.OrderDto;
import com.netoptc.DsCommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private  OrderService orderService;


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(
            @PathVariable Long id
    ) {
        OrderDto result =  orderService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/")
    public ResponseEntity<OrderDto> insert(@RequestBody OrderDto dto) {
        OrderDto result = orderService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }
}
