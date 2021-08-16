package com.pawan.ecommerce.service;

import com.pawan.ecommerce.dto.CartRequest;
import com.pawan.ecommerce.model.Cart;
import com.pawan.ecommerce.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;

    public void createProduct(CartRequest cartRequest) {
        Cart cart = new Cart();
        cart.setUser(cartRequest.getUser());
        cart.setTotal(cartRequest.getTotal());
        cart.setQuantity(cartRequest.getQuantity());
        cart.setProduct(cartRequest.getProduct());

        cartRepo.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }

    public Cart getCartByID(int id) {
        return cartRepo.findById(id).get();
    }

    public void updateCart(Cart cart, CartRequest cartRequest) {

        cart.setUser(cartRequest.getUser());
        cart.setProduct(cartRequest.getProduct());
        cart.setQuantity(cartRequest.getQuantity());
        cart.setTotal(cartRequest.getTotal());

        cartRepo.save(cart);
    }
}
