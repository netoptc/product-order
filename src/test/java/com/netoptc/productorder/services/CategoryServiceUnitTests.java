package com.netoptc.productorder.services;

import com.netoptc.productorder.dtos.CategoryDto;
import com.netoptc.productorder.entities.Category;
import com.netoptc.productorder.exceptions.BadRequestException;
import com.netoptc.productorder.exceptions.ResourceNotFoundException;
import com.netoptc.productorder.factories.CategoryFactory;
import com.netoptc.productorder.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class CategoryServiceUnitTests {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private Long existingId, nonExistingId;
    private String validCategoryName, invalidCategoryName;
    private Category category;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 999L;
        validCategoryName = "Category name valid";
        invalidCategoryName = "Category name invalid !@#";
        category = CategoryFactory.createCategory();


        Mockito.when(categoryRepository.findById(existingId)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Mockito.doThrow(ResourceNotFoundException.class).when(categoryRepository).findById(nonExistingId);

    }

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

    @Test
    public void findByIdShouldReturnCategoryWhenExistsId() {
        Category result =  categoryService.findById(existingId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(category.getName(), result.getName());
    }

    @Test
    public void findByIdShouldReturnCategoryWhenNonExistsId() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.findById(nonExistingId);
        });
    }
}
