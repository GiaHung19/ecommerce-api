package com.giahung19.ecommerce_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRequestDTO {
    
    @NotNull (message = "Product must have category")
    private Long categoryId;

    @NotBlank (message = "Name of product not blank")
    private String name;

    @NotNull 
    @Positive 
    private Double price;


    @Min (value = 0)
    private Integer stockQuantity;

    public ProductRequestDTO() {}

    public ProductRequestDTO(Long categoryId,String name, Double price, Integer stockQuantity) {
        this.categoryId=categoryId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

}
