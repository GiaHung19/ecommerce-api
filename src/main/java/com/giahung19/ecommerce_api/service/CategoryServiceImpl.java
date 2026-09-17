package com.giahung19.ecommerce_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.giahung19.ecommerce_api.entity.*;
import com.giahung19.ecommerce_api.repository.CategoryRepository;


@Service 
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired 
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    // CRUD 

    // find all
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }  

    // find 
    public Category findById(Long id){
        return categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found category with id: " + id));
    }
    
    // save 
    public Category save(Category category){
        return categoryRepository.save(category);
    }

    // delete 
    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
}
