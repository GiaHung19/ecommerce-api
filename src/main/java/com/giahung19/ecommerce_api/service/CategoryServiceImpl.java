package com.giahung19.ecommerce_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.giahung19.ecommerce_api.entity.*;
import com.giahung19.ecommerce_api.repository.CategoryRepository;
import com.giahung19.ecommerce_api.dto.CategoryRequestDTO;
import com.giahung19.ecommerce_api.dto.CategoryResponseDTO;





@Service 
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired 
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    private CategoryResponseDTO convertToResponseDTO(Category category){
        CategoryResponseDTO dto =new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    // CRUD 

    // find all
    @Override
    public List<CategoryResponseDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }
 

    // find 
    public CategoryResponseDTO findById(Long id){
        Category category= categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found category with id: " + id));
        return convertToResponseDTO(category);
    }
    
    // save 
    public CategoryResponseDTO save(CategoryRequestDTO requestDTO){
        Category category=new Category();
        category.setName(requestDTO.getName());
        category.setDescription(requestDTO.getDescription());

        Category savedDB=categoryRepository.save(category);

        return convertToResponseDTO(savedDB);
    }

    // update 
    public CategoryResponseDTO update(Long id, CategoryRequestDTO requestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found category with id: " + id));

        category.setName(requestDTO.getName());
        category.setDescription(requestDTO.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return convertToResponseDTO(updatedCategory);
    }

    // delete 
    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
}
