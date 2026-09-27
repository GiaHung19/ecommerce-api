package com.giahung19.ecommerce_api.service;

import com.giahung19.ecommerce_api.dto.*;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponseDTO findById(Long id);
    ProductResponseDTO save(ProductRequestDTO requestDTO);
    ProductResponseDTO update(Long id, ProductRequestDTO requestDTO);
    void deleteById(Long id);
    Page<ProductResponseDTO> findAll(int page,int size,String sortBy,String sortDir);

}
