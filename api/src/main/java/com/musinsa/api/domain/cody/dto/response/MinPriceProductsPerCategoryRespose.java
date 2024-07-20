package com.musinsa.api.domain.cody.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.musinsa.core.domain.product.dto.MinPriceProductPerCategory;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MinPriceProductsPerCategoryRespose {
    private List<MinPriceProductPerCategory> list;

    private BigDecimal totalPrice;
}
