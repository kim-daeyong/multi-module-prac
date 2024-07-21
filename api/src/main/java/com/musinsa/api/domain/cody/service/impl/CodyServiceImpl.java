package com.musinsa.api.domain.cody.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.musinsa.api.domain.cody.dto.response.MinAndMaxPriceProductByCategoryResponse;
import com.musinsa.api.domain.cody.dto.response.AllCategoryMinPriceBrandResponse;
import com.musinsa.api.domain.cody.dto.response.CategoryMinPriceBrand;
import com.musinsa.core.domain.product.dto.MinAndMaxPriceByCategoryResult;
import com.musinsa.core.domain.product.dto.CategoryMinPriceResult;
import org.springframework.stereotype.Service;

import com.musinsa.api.domain.cody.dto.response.MinPriceProductsPerCategoryRespose;
import com.musinsa.api.domain.cody.service.CodyService;
import com.musinsa.core.domain.product.dto.MinPriceProductPerCategory;
import com.musinsa.core.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import static com.musinsa.core.common.constant.CodyConstant.ALL_CATEGORY_FOR_TARGET;

@Service
@RequiredArgsConstructor
public class CodyServiceImpl implements CodyService {
    private final ProductRepository productRepository;

    @Override
    public MinPriceProductsPerCategoryRespose getMinPriceProductsPerCategory() {
        List<MinPriceProductPerCategory> list = productRepository.getMinPriceProductPerCategory();
        
        return MinPriceProductsPerCategoryRespose.builder()
            .list(list)
            .totalPrice(list.stream()
                            .map(MinPriceProductPerCategory::getPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add))
            .build();
    }

    @Override
    public AllCategoryMinPriceBrandResponse getAllCategoryMinPriceBrand() {
        List<CategoryMinPriceResult> result = productRepository.getAllCategoryMinPriceBrand(ALL_CATEGORY_FOR_TARGET);

        return AllCategoryMinPriceBrandResponse.builder()
                .brand(result.getFirst().getBrandName())
                .categories(result.stream()
                        .map(res -> CategoryMinPriceBrand.builder()
                                .categoryName(res.getCategoryName())
                                .price(res.getPrice())
                                .build())
                        .toList())
                .totalPrice(result.stream()
                        .map(CategoryMinPriceResult::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }

    @Override
    public MinAndMaxPriceProductByCategoryResponse getMinAndMaxPriceProductByCategory(String categoryName) {
        MinAndMaxPriceByCategoryResult result = productRepository.findMinMaxPriceByCategory(categoryName);

        return MinAndMaxPriceProductByCategoryResponse.builder()
                .category(categoryName)
                .minPrice(result.getMinPrice())
                .maxPrice(result.getMaxPrice())
                .build();
    }

}
