package com.musinsa.core.domain.product.dto;

import com.musinsa.core.domain.brand.dto.BrandDto;
import com.musinsa.core.domain.category.dto.CategoryDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductDtoWithBrandAndCategory extends ProductDto {
    private BrandDto brand;

    private CategoryDto category;
}
