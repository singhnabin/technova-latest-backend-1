package com.pawan.ecommerce.ecommerce.service;

import com.pawan.ecommerce.ecommerce.model.Cart;
import com.pawan.ecommerce.ecommerce.repo.CartRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartServiceTest {
    @Autowired
    private CartService cartService;



    @MockBean
    private CartRepo cartRepo;

    @Test
    void createProduct() {
//        mockMvc.perform(get("/")).andExpect(")

    }


    @Test
    void getAllCarts() {
        when(cartRepo.findAll()).thenReturn(Arrays.asList(new Cart(1, 500, "nabinsingh@gmail.com", 5, "Iphone"),new Cart(3, 500, "nabinsingh@gmail.com", 5, "Iphone")));
        assertEquals(2,cartService.getAllCarts().size());
    }

    @Test
    void getCartByID() {
        when(cartRepo.findById(1)).thenReturn(java.util.Optional.of(new Cart(1, 500, "nabinsingh@gmail.com", 5, "Iphone")));
        assertEquals(1,cartService.getCartByID(1).getId());
    }

    @Test
    void updateCart() {
    }

}