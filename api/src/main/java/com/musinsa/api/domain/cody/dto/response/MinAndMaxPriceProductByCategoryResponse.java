package com.musinsa.api.domain.cody.dto.response;


import com.musinsa.core.domain.product.dto.MinAndMaxPriceProductByCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MinAndMaxPriceProductByCategoryResponse {
    private String category;

    private MinAndMaxPriceProductByCategory minPrice;

    private MinAndMaxPriceProductByCategory maxPrice;
}
