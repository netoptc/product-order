package com.netoptc.productorder.services;

import com.netoptc.productorder.dtos.CategoryDto;
import com.netoptc.productorder.exceptions.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class CategoryServiceIntegrationTests {

    @Autowired
    private CategoryService categoryService;

    private String validCategoryName, invalidCategoryName;

    @BeforeEach
    void setUp() {

        validCategoryName = "Category name valid";
        invalidCategoryName = "Category name invalid !@#";}

    @Test
    public void insertShouldReturnCategoryDtoWhenValidCategoryName() {
        CategoryDto categoryDto =  categoryService.insert(validCategoryName);
        Assertions.assertEquals(validCategoryName, categoryDto.getName());
    }

    @Test
    public void insertShouldThrowWhenInvalidCategoryName() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            categoryService.insert(invalidCategoryName);
        });
    }
}
