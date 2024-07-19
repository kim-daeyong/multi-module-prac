package com.musinsa.bo.api.domain.product.service.impl;

import org.springframework.stereotype.Service;

import com.musinsa.bo.api.domain.product.dto.request.CreateProductRequest;
import com.musinsa.bo.api.domain.product.dto.request.DeleteProductRequest;
import com.musinsa.bo.api.domain.product.dto.request.UpdateProductRequest;
import com.musinsa.bo.api.domain.product.service.ProductService;
import com.musinsa.core.common.exception.custom.NotFoundException;
import com.musinsa.core.common.message.ResponseCode;
import com.musinsa.core.domain.brand.entity.Brand;
import com.musinsa.core.domain.brand.repository.BrandRepository;
import com.musinsa.core.domain.category.entity.Category;
import com.musinsa.core.domain.category.repository.CategoryRepository;
import com.musinsa.core.domain.product.dto.CreateProduct;
import com.musinsa.core.domain.product.dto.ProductDtoWithBrandAndCategory;
import com.musinsa.core.domain.product.entity.Product;
import com.musinsa.core.domain.product.mapper.ProductMapper;
import com.musinsa.core.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDtoWithBrandAndCategory createProduct(CreateProductRequest createProductRequest) {
        Brand brand = brandRepository.findById(createProductRequest.getBrandId())
            .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_BRAND_EXCEPTION));

        Category category = categoryRepository.findById(createProductRequest.getCategoryId())
            .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_CATEGORY_EXCEPTION));

        Product product = Product.of(CreateProduct.builder()
                                        .name(createProductRequest.getName())
                                        .price(createProductRequest.getPrice())
                                        .brand(brand)
                                        .category(category)
                                        .build());

        return productMapper.asDtoWithBrandAndCategory(productRepository.save(product));
    }

    @Override
    public ProductDtoWithBrandAndCategory updateProduct(UpdateProductRequest updateProductRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public void deleteProduct(DeleteProductRequest deleteProductRequest) {
        productRepository.deleteAllByIdInBatch(deleteProductRequest.getIds());
    }
    
}
