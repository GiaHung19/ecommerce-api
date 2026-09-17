package com.giahung19.ecommerce_api.service;

import com.giahung19.ecommerce_api.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
    void deleteById(Long id);
}