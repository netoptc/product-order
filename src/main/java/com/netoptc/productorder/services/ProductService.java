package com.netoptc.productorder.services;


import com.netoptc.productorder.dtos.ProductDto;
import com.netoptc.productorder.dtos.ProductMinDto;
import com.netoptc.productorder.entities.Category;
import com.netoptc.productorder.entities.Product;
import com.netoptc.productorder.exceptions.BadRequestException;
import com.netoptc.productorder.exceptions.ResourceNotFoundException;
import com.netoptc.productorder.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Page<ProductMinDto> findAll(Pageable pageable, String name) {
        Page<Product> result = productRepository.findAllByName(pageable, name);
        return  result.map(ProductMinDto::new);
    }



    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Product result = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return  new ProductDto(result);
    }


    public ProductDto insert(ProductDto dto) {

        Product product = new Product(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getImgUrl());
        Set<Category> categories = new HashSet<>();

        dto.getCategories().forEach(categoryDto -> {
            Category category = categoryService.findById(categoryDto.getId());
            categories.add(category);
        });

        product.setCategories(categories);

        productRepository.save(product);

        return new ProductDto(product);
    }


    public ProductDto update(Long id, ProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());

        Set<Category> categories = new HashSet<>();

        dto.getCategories().forEach(categoryDto -> {
            Category category = categoryService.findById(categoryDto.getId());
            categories.add(category);
        });

        product.setCategories(categories);

        productRepository.save(product);

        return new ProductDto(product);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        try {
            productRepository.delete(product);
        }  catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Falha de integridade referencial");
        }
    }

}
