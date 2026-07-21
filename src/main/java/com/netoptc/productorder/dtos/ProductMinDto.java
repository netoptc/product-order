package com.netoptc.productorder.dtos;

import com.netoptc.productorder.entities.Product;

public class ProductMinDto {

    private Long id;
    private String name;
    private Double price;
    private String imgUrl;

    public ProductMinDto(Long id, String name, String imgUrl, Double price) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public ProductMinDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.imgUrl = entity.getImgUrl();
        this.price = entity.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Double getPrice() {
        return price;
    }
}
