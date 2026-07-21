package com.netoptc.productorder.dtos;

import com.netoptc.productorder.entities.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDto {

    private Long id;

    @Length(min = 3, max = 80, message = "Nome deve ter entre 3 e 80 caracteres")
    private String name;

    @Length(min = 10, message = "Descrição não pode ter menos que 10 caracteres")
    private String description;

    @Positive(message = "preço deve ser maior que zero")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "Deve haver pelo menos uma categoria")
    private Set<CategoryDto> categories = new HashSet<>();


    public ProductDto() { };
    public ProductDto(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }


    public ProductDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.categories = entity.getCategories().stream().map(CategoryDto::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() { return description; }

    public String getImgUrl() {
        return imgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public Set<CategoryDto> getCategories() {
        return categories;
    }
}
