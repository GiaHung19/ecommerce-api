package com.giahung19.ecommerce_api.service;

import com.giahung19.ecommerce_api.entity.Product;
import java.util.*;




public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
