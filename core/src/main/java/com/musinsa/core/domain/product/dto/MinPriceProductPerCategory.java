package com.musinsa.core.domain.product.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinPriceProductPerCategory {
    private String category;
    private String brand;
    private BigDecimal price;
}
