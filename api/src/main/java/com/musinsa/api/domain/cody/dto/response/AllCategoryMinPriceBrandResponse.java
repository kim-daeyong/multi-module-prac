package com.musinsa.api.domain.cody.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class AllCategoryMinPriceBrandResponse {
    private String brand;

    private List<CategoryMinPriceBrand> categories;

    private BigDecimal totalPrice;
    
}
