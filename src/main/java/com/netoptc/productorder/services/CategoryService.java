package com.netoptc.productorder.services;


import com.netoptc.productorder.dtos.CategoryDto;
import com.netoptc.productorder.entities.Category;
import com.netoptc.productorder.exceptions.BadRequestException;
import com.netoptc.productorder.exceptions.ResourceNotFoundException;
import com.netoptc.productorder.repositories.CategoryRepository;
import com.netoptc.productorder.utils.StringValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDto insert(String name) {

        Boolean isValidName = StringValidateUtils.isAlphaWithSpaces(name);
        if (!isValidName) {
            throw  new BadRequestException("Invalid category name");
        }

        Category category = new Category(name);
        categoryRepository.save(category);
        return new CategoryDto(category);

    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public CategoryDto handleFindById(Long id) {
        Category category = findById(id);
        return  new CategoryDto(category);
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).toList();
    }
}

