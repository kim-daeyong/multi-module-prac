package com.musinsa.core.domain.product.dto;

import java.math.BigDecimal;

import com.musinsa.core.domain.brand.entity.Brand;
import com.musinsa.core.domain.category.entity.Category;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateProduct {
    private String name;

    private BigDecimal price;

    private Brand brand;

    private Category category;
}
