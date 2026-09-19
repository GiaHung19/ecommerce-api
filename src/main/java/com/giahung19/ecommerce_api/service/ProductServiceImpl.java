package com.giahung19.ecommerce_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.giahung19.ecommerce_api.entity.*;
import com.giahung19.ecommerce_api.repository.*;
import com.giahung19.ecommerce_api.dto.*;



@Service 
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Autowired 
    public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    } 

    private ProductResponseDTO convertToResponseDTO(Product product){
        ProductResponseDTO dto =new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
        }
        return dto;
    }

    public List<ProductResponseDTO> findAll(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public ProductResponseDTO findById(Long id){
        Product product= productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found product with id: "+id));
        return convertToResponseDTO(product);
    }

    public ProductResponseDTO save(ProductRequestDTO requestDTO){
        Category category =categoryRepository.findById(requestDTO.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Not found category with id: "+requestDTO.getCategoryId()));
        
        Product product=new Product();
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setStockQuantity(requestDTO.getStockQuantity());
        product.setCategory(category);
        
        Product savedDB=productRepository.save(product);

        return convertToResponseDTO(savedDB);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO requestDTO){
        Product product= productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found product with id: "+id));
        
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + requestDTO.getCategoryId()));
            
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setStockQuantity(requestDTO.getStockQuantity());
        product.setCategory(category);

        Product savedDB=productRepository.save(product);
        return convertToResponseDTO(savedDB);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

}
