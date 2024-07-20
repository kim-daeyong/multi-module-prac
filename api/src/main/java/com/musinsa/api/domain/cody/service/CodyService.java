package com.musinsa.api.domain.cody.service;

import com.musinsa.api.domain.cody.dto.response.MinAndMaxPriceProductByCategoryResponse;
import com.musinsa.api.domain.cody.dto.response.MinPriceAllCategoryPerBrandResponse;
import com.musinsa.api.domain.cody.dto.response.MinPriceProductsPerCategoryRespose;

public interface CodyService {
    MinPriceProductsPerCategoryRespose getMinPriceProductsPerCategory();

    MinPriceAllCategoryPerBrandResponse getMinPriceProductByBrand();

    MinAndMaxPriceProductByCategoryResponse getMinAndMaxPriceProductByCategory(String categoryName);
}
