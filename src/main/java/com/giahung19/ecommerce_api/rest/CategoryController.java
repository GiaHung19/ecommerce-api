package com.giahung19.ecommerce_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.giahung19.ecommerce_api.service.*;
import tools.jackson.databind.json.JsonMapper;
import com.giahung19.ecommerce_api.entity.*;
import java.util.*;

@RestController 
@RequestMapping ("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final JsonMapper jsonMapper;

    @Autowired 
    public CategoryController (CategoryService categoryService,JsonMapper jsonMapper){
        this.categoryService=categoryService;
        this.jsonMapper=jsonMapper;
    }

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping ("/{id}")
    public Category findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        category.setId(null);
        return categoryService.save(category);
    }

    @PutMapping ("/{id}")
    public Category updateCategory(@RequestBody Category category){
        return categoryService.save(category);
    }

    @PatchMapping ("/{id}")
    public Category patchCategory(@PathVariable Long id,@RequestBody Map<String,Object> patchPayload){
        Category category =categoryService.findById(id);
        if(category==null){
            throw new RuntimeException("Not found category with id: "+id);
        }
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Category id not allow in request body");
        }
        Category patchedCategory =jsonMapper.updateValue(category,patchPayload);
        return categoryService.save(patchedCategory);
    }   

    @DeleteMapping ("/{id}")
    public String deleteCategory(@PathVariable Long id){
        Category category =categoryService.findById(id);
        if(category==null){
            throw new RuntimeException("Not found category with id: "+id);
        }
        categoryService.deleteById(id);
        return "Delete category with id: "+id;
    }

}
