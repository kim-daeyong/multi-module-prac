package com.musinsa.core.domain.product.dto;

import java.math.BigDecimal;

import com.musinsa.core.domain.brand.entity.Brand;
import com.musinsa.core.domain.category.entity.Category;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateProduct {
    private String name;

    private BigDecimal price;

    private Brand brand;

    private Category category;
    
    public void updateBrand(Brand brand) {
        this.brand = brand;
    }

    public void updateCategory(Category category) {
        this.category = category;
    }
}