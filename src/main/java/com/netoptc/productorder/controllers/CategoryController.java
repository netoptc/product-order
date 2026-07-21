package com.netoptc.productorder.controllers;

import com.netoptc.productorder.dtos.CategoryDto;
import com.netoptc.productorder.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> findAll() {
        List<CategoryDto> list = categoryService.findAll();
        return ResponseEntity.ok(list);
    }
}
