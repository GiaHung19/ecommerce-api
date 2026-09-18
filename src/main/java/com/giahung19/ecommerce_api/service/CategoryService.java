package com.giahung19.ecommerce_api.service;


import com.giahung19.ecommerce_api.dto.CategoryRequestDTO;
import com.giahung19.ecommerce_api.dto.CategoryResponseDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> findAll();
    CategoryResponseDTO findById(Long id);
    CategoryResponseDTO save(CategoryRequestDTO requestDTO);
    CategoryResponseDTO update(Long id, CategoryRequestDTO requestDTO);
    void deleteById(Long id);
}