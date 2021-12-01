package com.pawan.ecommerce.ecommerce.controller;

import com.pawan.ecommerce.ecommerce.dto.CartRequest;
import com.pawan.ecommerce.ecommerce.model.Cart;
import com.pawan.ecommerce.ecommerce.service.CartService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
@NoArgsConstructor
public class CartController {

    private CartService cartService;

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
