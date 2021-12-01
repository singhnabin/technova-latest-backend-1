package com.pawan.ecommerce.ecommerce.service;

import com.pawan.ecommerce.ecommerce.dto.CategoryRequest;
import com.pawan.ecommerce.ecommerce.model.Category;
import com.pawan.ecommerce.ecommerce.repo.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceTest1 {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepo categoryRepo;

    @Test
    void createCategory() {
        CategoryRequest categoryRequest= new CategoryRequest("Automobile","nabinsinh@db.com");
        Category category=new Category();
        categoryService.createCategory(categoryRequest);
        category.setType(categoryRequest.getType());
        category.setCreated_by(categoryRequest.getCreated_by());
        verify(categoryRepo,times(1)).save(category);
    }

    @Test
    void getallCategory() {
        when(categoryRepo.findAll()).thenReturn(Stream.of(new Category(2, "Mobile", "nabinsinh@db.com"),new Category(3, "Mobile", "nabinsinh@db.com")).collect(Collectors.toList()));
    }

    @Test
    void getCategory() {
        when(categoryRepo.findById(2)).thenReturn(Optional.of(new Category(2, "Mobile", "nabinsinh@db.com")));
        assertEquals(2,categoryService.getCategory(2).get().getId());

    }

    @Test
    void deleteCategory() {
        Category cate=new Category(2, "Mobile", "nabinsinh@db.com");
        categoryService.deleteCategory(2);
        verify(categoryRepo,times(1)).deleteById(2);
    }

    @Test
    void updateCategory() {
        Category cate=new Category(2, "Mobile", "nabinsinh@db.com");
        CategoryRequest categoryRequest= new CategoryRequest("Automobile","nabinsinh@db.com");
        categoryService.updateCategory(cate,categoryRequest);
        verify(categoryRepo,times(1)).save(cate);



    }
}