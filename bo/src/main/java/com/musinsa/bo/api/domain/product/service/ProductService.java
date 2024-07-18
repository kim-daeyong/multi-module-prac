package com.musinsa.bo.api.domain.product.service;

import com.musinsa.bo.api.domain.product.dto.request.CreateProductRequest;
import com.musinsa.core.domain.product.dto.ProductDtoWithBrandAndCategory;

public interface ProductService {
    ProductDtoWithBrandAndCategory createProduct(CreateProductRequest createProductRequest);
}
