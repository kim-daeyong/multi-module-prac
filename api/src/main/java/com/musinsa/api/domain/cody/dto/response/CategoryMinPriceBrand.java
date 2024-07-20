package com.musinsa.api.domain.cody.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class CategoryMinPriceBrand {
    private String categoryName;

    private BigDecimal price;
}
