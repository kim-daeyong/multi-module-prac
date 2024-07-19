package com.musinsa.bo.api.domain.product.service;

import com.musinsa.bo.api.domain.product.dto.request.CreateProductRequest;
import com.musinsa.bo.api.domain.product.dto.request.DeleteProductRequest;
import com.musinsa.bo.api.domain.product.dto.request.UpdateProductRequest;
import com.musinsa.core.domain.product.dto.ProductDtoWithBrandAndCategory;

public interface ProductService {
    ProductDtoWithBrandAndCategory createProduct(CreateProductRequest createProductRequest);

    ProductDtoWithBrandAndCategory updateProduct(Long id, UpdateProductRequest updateProductRequest);

    void deleteProduct(DeleteProductRequest deleteProductRequest);
}
