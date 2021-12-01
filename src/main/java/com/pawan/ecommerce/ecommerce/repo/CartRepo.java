package com.pawan.ecommerce.ecommerce.repo;

import com.pawan.ecommerce.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
}
