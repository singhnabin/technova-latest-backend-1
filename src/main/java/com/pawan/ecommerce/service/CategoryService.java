package com.pawan.ecommerce.service;

import com.pawan.ecommerce.dto.CategoryRequest;
import com.pawan.ecommerce.model.Category;
import com.pawan.ecommerce.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryrepo;


    public void createCategory(CategoryRequest categoryrequest) {
        Category category = new Category();
        category.setType(categoryrequest.getType());
        category.setCreated_by(categoryrequest.getCreated_by());
        categoryrepo.save(category);

    }


    public List<Category> getallCategory() {
        return categoryrepo.findAll();
    }

    public Optional<Category> getCategory(int id) {

        return categoryrepo.findById(id);
    }

    public void deleteCategory(int id) {
        categoryrepo.deleteById(id);
    }

    public void updateCategory(Category category, CategoryRequest categoryRequest) {

        category.setCreated_by(categoryRequest.getCreated_by());
        category.setType(categoryRequest.getType());

        categoryrepo.save(category);
    }
}
