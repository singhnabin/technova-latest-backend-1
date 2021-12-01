//package com.pawan.ecommerce.ecommerce.service;
//
//import com.pawan.ecommerce.ecommerce.dto.CategoryRequest;
//import com.pawan.ecommerce.ecommerce.model.Category;
//import com.pawan.ecommerce.ecommerce.repo.CategoryRepo;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class CategoryServiceTest {
//    @Autowired
//    private CategoryService categoryService;
//
//    @MockBean
//    private CategoryRepo categoryRepo;
//
//
//    @Test
//    void createCategory() {
//        CategoryRequest newCate= new CategoryRequest("Electronics","nabinsingh34@gmail.com");
////        doNothing().when(categoryRepo.save(any(CategoryRequest.class));
//        categoryService.createCategory(newCate);
//        verify(categoryRepo,times(1)).save(newCate);
//       // when(categoryRepo.save(new Category(1,"Electronics","nabinsingh34@gmail.com"))).thenReturn(new Category(1,"Electronics","nabinsingh34@gmail.com"));
//       // assertEquals(1,categoryService.createCategory(new CategoryRequest("Electronics","nabinsingh34@gmail.com")));
//    }
//
//    @Test
//    void getallCategory() {
//        when(categoryRepo.findAll()).thenReturn(Stream.of(new Category(1,"Electronics","nabinsingh34@gmail.com"),new Category(2,"Cell Phones","nabinsingh34@gmail.com")).collect(Collectors.toList()));
//        assertEquals(2,categoryService.getallCategory().size());
//    }
//
//    @Test
//    void getCategory() {
//        when(categoryRepo.findById(1)).thenReturn(java.util.Optional.of(new Category(1, "Electronics", "nabinsingh34@gmail.com")));
//        assertEquals(1,categoryService.getCategory(1).get().getId());
//    }
//
//    @Test
//    void deleteCategory() {
//        Category cate=new Category(5,"Electronics","nabinsingh34@gmail.com");
//        categoryService.deleteCategory(cate.getId());
//        verify(categoryRepo,times(1)).deleteById(cate.getId());
//    }
//
//    @Test
//    void updateCategory() {
//    }
//}