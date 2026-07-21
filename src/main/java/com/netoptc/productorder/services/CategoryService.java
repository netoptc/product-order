package com.netoptc.productorder.services;


import com.netoptc.productorder.dtos.CategoryDto;
import com.netoptc.productorder.entities.Category;
import com.netoptc.productorder.exceptions.ResourceNotFoundException;
import com.netoptc.productorder.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).toList();
    }

}

