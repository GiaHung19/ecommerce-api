package com.giahung19.ecommerce_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.giahung19.ecommerce_api.service.ProductService;
import tools.jackson.databind.json.JsonMapper;
import java.util.*;
import com.giahung19.ecommerce_api.entity.*;



@RequestMapping ("/api/products")
@RestController 
public class ProductController {
    
    private final ProductService productService;
    private final JsonMapper jsonMapper;

    @Autowired 
    public ProductController (ProductService productService,JsonMapper jsonMapper){
        this.jsonMapper=jsonMapper;
        this.productService=productService;
    }

    @GetMapping 
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping ("/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping 
    public Product createProduct(@RequestBody Product product){
        product.setId(null);
        return productService.save(product);
    }

    @PutMapping ("/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody Product product){
        product.setId(id);
        return productService.save(product);
    }

    @PatchMapping ("/{id}")
    public Product patchProduct(@PathVariable Long id,@RequestBody Map<String,Object> patchPayload){
        Product product =productService.findById(id);
        if(product ==null){
            throw new RuntimeException("Not found product with id: "+id);
        }
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Product id not allow in request body");
        }
        Product patchedProduct =jsonMapper.updateValue(product,patchPayload);
        return productService.save(patchedProduct);
    }

    @DeleteMapping ("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Product product=productService.findById(id);
        if(product == null){
            throw new RuntimeException("Not found product with id: ");
        }
        productService.deleteById(id);
        return "Deleted product with id: "+id;
    }


}
