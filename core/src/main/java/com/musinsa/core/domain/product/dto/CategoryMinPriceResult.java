package com.musinsa.core.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class CategoryMinPriceResult {
    private Long productId;
    private String productName;
    private Long brandId;
    private String brandName;
    private Long categoryId;
    private String categoryName;
    private BigDecimal price;
}
