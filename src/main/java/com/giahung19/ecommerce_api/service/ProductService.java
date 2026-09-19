package com.giahung19.ecommerce_api.service;

import java.util.*;
import com.giahung19.ecommerce_api.dto.*;

public interface ProductService {
    List<ProductResponseDTO> findAll();
    ProductResponseDTO findById(Long id);
    ProductResponseDTO save(ProductRequestDTO requestDTO);
    ProductResponseDTO update(Long id, ProductRequestDTO requestDTO);
    void deleteById(Long id);
}
