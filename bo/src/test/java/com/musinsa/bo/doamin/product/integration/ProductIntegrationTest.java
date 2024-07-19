package com.musinsa.bo.doamin.product.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.musinsa.bo.api.domain.product.dto.request.CreateProductRequest;
import com.musinsa.bo.api.domain.product.dto.request.DeleteProductRequest;
import com.musinsa.bo.api.domain.product.service.ProductService;
import com.musinsa.core.common.exception.custom.NotFoundException;
import com.musinsa.core.common.message.ResponseCode;
import com.musinsa.core.domain.brand.entity.Brand;
import com.musinsa.core.domain.brand.repository.BrandRepository;
import com.musinsa.core.domain.category.entity.Category;
import com.musinsa.core.domain.category.repository.CategoryRepository;
import com.musinsa.core.domain.product.dto.ProductDtoWithBrandAndCategory;
import com.musinsa.core.domain.product.mapper.ProductMapper;
import com.musinsa.core.domain.product.repository.ProductRepository;
import com.musinsa.core.domain.product.repository.querydsl.ProductQuerydslRepository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestPropertySource(properties = {
    "spring.sql.init.mode=never"
})
@SpringBootTest
public class ProductIntegrationTest {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    // @Autowired
    // private ProductQuerydslRepository productQuerydslRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        brandRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void createProductTest() {
        // Given
        Brand brand = brandRepository.save(Brand.builder()
                                                .name("test")
                                                .build());
        Category category = categoryRepository.save(Category.builder()
                                                                .name("test")
                                                                .build());

        CreateProductRequest request = CreateProductRequest.builder()
                                            .brandId(brand.getId())
                                            .categoryId(category.getId())
                                            .name("test")
                                            .price(BigDecimal.valueOf(3000))
                                            .build();
        
        // When
        ProductDtoWithBrandAndCategory result = productService.createProduct(request);

        // Then
        assertNotNull(result);
        assertEquals("test", result.getName());
        assertEquals(BigDecimal.valueOf(3000), result.getPrice());
        assertEquals(brand.getName(), result.getBrand().getName());
        assertEquals(category.getName(), result.getCategory().getName());
    }

    @Test
    void createProduct_not_exist_brand() {
        // Given
        CreateProductRequest request = CreateProductRequest.builder()
                                            .brandId(1L)
                                            .categoryId(1L)
                                            .name("test")
                                            .price(BigDecimal.valueOf(3000))
                                            .build();

        // When
        // Then
        assertThatThrownBy(() -> {
            productService.createProduct(request);
        }).isInstanceOf(NotFoundException.class)
                .hasMessageContaining(ResponseCode.NOT_FOUND_BRAND_EXCEPTION.getMessage());
    }

    @Test
    void createProduct_not_exist_category() {
        // Given
        Brand brand = brandRepository.save(Brand.builder()
                                                .name("test")
                                                .build());

        CreateProductRequest request = CreateProductRequest.builder()
                                            .brandId(brand.getId())
                                            .categoryId(1L)
                                            .name("test")
                                            .price(BigDecimal.valueOf(3000))
                                            .build();

        // When
        // Then
        assertThatThrownBy(() -> {
            productService.createProduct(request);
        }).isInstanceOf(NotFoundException.class)
                .hasMessageContaining(ResponseCode.NOT_FOUND_CATEGORY_EXCEPTION.getMessage());
    }

    @Test
    void deleteProductTest() {
        // Given
        Brand brand = brandRepository.save(Brand.builder()
                                                .name("test")
                                                .build());
        Category category = categoryRepository.save(Category.builder()
                                                                .name("test")
                                                                .build());

        CreateProductRequest request = CreateProductRequest.builder()
                                            .brandId(brand.getId())
                                            .categoryId(category.getId())
                                            .name("test")
                                            .price(BigDecimal.valueOf(3000))
                                            .build();

        ProductDtoWithBrandAndCategory result = productService.createProduct(request);

        DeleteProductRequest deleteProductRequest = DeleteProductRequest.builder()
                                                        .ids(List.of(result.getId()))
                                                        .build();
        
        // When
        productService.deleteProduct(deleteProductRequest);

        // Then
        assertFalse(productRepository.existsById(result.getId()));
    }
}
