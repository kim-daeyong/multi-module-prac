package com.musinsa.api.domain.cody.service;

import com.musinsa.api.domain.cody.dto.response.MinAndMaxPriceProductByCategoryResponse;
import com.musinsa.api.domain.cody.dto.response.AllCategoryMinPriceBrandResponse;
import com.musinsa.api.domain.cody.dto.response.MinPriceProductsPerCategoryRespose;

public interface CodyService {
    MinPriceProductsPerCategoryRespose getMinPriceProductsPerCategory();

    AllCategoryMinPriceBrandResponse getAllCategoryMinPriceBrand();

    MinAndMaxPriceProductByCategoryResponse getMinAndMaxPriceProductByCategory(String categoryName);
}
