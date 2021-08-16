package com.pawan.ecommerce.controller;

import com.pawan.ecommerce.dto.CartRequest;
import com.pawan.ecommerce.dto.ProductRequest;
import com.pawan.ecommerce.model.Cart;
import com.pawan.ecommerce.service.CartService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping(value="/create",produces="application/json")
    public ResponseEntity<?> createProduct(@RequestBody CartRequest cartRequest){
        JSONObject jsonObject = new JSONObject();
        cartService.createProduct(cartRequest);
        jsonObject.put("Status", 200);
        jsonObject.put("message", "Sucessfully added user");
        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());

    }

    @GetMapping("/getallCart")
    public List<Cart> getallCart(){

        return cartService.getAllCarts();
    }

    @GetMapping("getCart/{id}")
    public Cart getCartById(@PathVariable int id){

        return cartService.getCartByID(id);
    }

    @PutMapping("updateCart/{id}")
    public void updateCart(@PathVariable int id , @RequestBody CartRequest cartRequest){
        Cart cart = cartService.getCartByID(id);
        cartService.updateCart(cart,cartRequest);
    }
}
