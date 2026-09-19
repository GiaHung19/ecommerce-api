package com.giahung19.ecommerce_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.giahung19.ecommerce_api.dto.*;
import com.giahung19.ecommerce_api.service.ProductService;
import jakarta.validation.Valid;
import tools.jackson.databind.json.JsonMapper;
import java.util.*;



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
     public ResponseEntity<List<ProductResponseDTO>> findAll(){
        List<ProductResponseDTO> list=productService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        ProductResponseDTO productResponseDTO =productService.findById(id);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PostMapping 
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO createProduct =productService.save(requestDTO);
        return new ResponseEntity<>(createProduct,HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequestDTO requestDTO){
        ProductResponseDTO updateProduct =productService.update(id, requestDTO);
        return ResponseEntity.ok(updateProduct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> patchProduct(@PathVariable Long id,@RequestBody Map<String, Object> patchPayload) {
        
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Product id not allowed in request body");
        }

        ProductResponseDTO currentDTO = productService.findById(id);
        ProductResponseDTO patchedDTO = jsonMapper.updateValue(currentDTO, patchPayload);
        ProductRequestDTO requestDTO = new ProductRequestDTO(patchedDTO.getCategoryId(),patchedDTO.getName(),patchedDTO.getPrice(),patchedDTO.getStockQuantity());
        ProductResponseDTO updatedCategory = productService.update(id, requestDTO);
        return ResponseEntity.ok(updatedCategory);
    }  

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.findById(id);
        productService.deleteById(id);
        return ResponseEntity.ok("Deleted product with id: " + id);
    }


}
