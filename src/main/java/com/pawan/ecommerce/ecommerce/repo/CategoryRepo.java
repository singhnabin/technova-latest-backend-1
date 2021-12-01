package com.pawan.ecommerce.ecommerce.repo;

import com.pawan.ecommerce.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
