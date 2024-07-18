package com.musinsa.bo.api.domain.product.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateProductRequest {
    @NotBlank(message = "상품명을 입력해주세요.")
    private String name;

    @NotNull(message = "가격을 입력해주세요.")
    private BigDecimal price;

    @NotNull(message = "브랜드를 입력해주세요.")
    private Long brandId;

    @NotNull(message = "카테고리를 입력해주세요.")
    private Long categoryId;   
}
