package com.netoptc.productorder.dtos;

import com.netoptc.productorder.entities.Category;

public class CategoryDto {

    private Long id;
    private String name;


    public CategoryDto() { }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
