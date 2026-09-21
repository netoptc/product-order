package com.netoptc.productorder.repositories;


import com.netoptc.productorder.entities.Category;
import com.netoptc.productorder.factories.CategoryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class CategoryRepositoryIntegrationTests {

    private Long existingId, nonExistingId;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 999L;
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void saveShouldPersistObjectWhenIdIsNull() {
        Category category = CategoryFactory.createCategory();
        category.setId(null);
        categoryRepository.save(category);
        Assertions.assertNotNull(category.getId());
    }

    @Test
    public void saveShouldUpdateObjectWhenIdExists() {
        Category category = categoryRepository.getReferenceById(existingId);
        category.setName("New name");
        categoryRepository.save(category);
        Assertions.assertEquals("New name", category.getName());
    }


    @Test
    public void findByIdShouldReturnObjectWhenIdExists() {
        Optional<Category> category = categoryRepository.findById(existingId);
        Assertions.assertTrue(category.isPresent());
    }

    @Test
    public void findByIdShouldNotReturnObjectWhenNonExistsId() {
        Optional<Category> category = categoryRepository.findById(nonExistingId);
        Assertions.assertTrue(category.isEmpty());
    }

    @Test
    public void findAllShouldReturnObjectList() {
        List<Category> categoryList =  categoryRepository.findAll();
        Assertions.assertFalse(categoryList.isEmpty());
    }


    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        categoryRepository.deleteById(existingId);
        Optional<Category> category = categoryRepository.findById(existingId);
        Assertions.assertTrue(category.isEmpty());
    }
}
