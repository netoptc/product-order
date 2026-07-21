package com.netoptc.productorder.entities;


import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItemKey {
    private Long productId;
    private Long orderId;

    public OrderItemKey() {}

    public OrderItemKey(Long productId, Long orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}

