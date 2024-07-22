package com.musinsa.bo.doamin.product.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import com.musinsa.bo.config.redis.TestEmbeddedRedisConfig;
import com.musinsa.bo.config.redis.TestRedisConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

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
import com.musinsa.core.domain.product.dto.ProductDtoWithBrandAndCategory;
import com.musinsa.core.domain.product.mapper.ProductMapper;
import com.musinsa.core.domain.product.repository.ProductRepository;
import com.musinsa.core.domain.product.repository.querydsl.ProductQuerydslRepository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@ContextConfiguration(classes = {TestRedisConfig.class, TestEmbeddedRedisConfig.class})
@TestPropertySource(properties = {
    "spring.sql.init.mode=never"
})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
public class ProductIntegrationTest {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

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

    @Test
    void updateProductTest() {
        // Given
        Brand brand = brandRepository.save(Brand.builder()
                                                .name("test")
                                                .build());
        Brand brand2 = brandRepository.save(Brand.builder()
                                                .name("test2")
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

        ProductDtoWithBrandAndCategory productDto = productService.createProduct(request);

        UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
                                                        .brandId(brand2.getId())
                                                        .categoryId(category.getId())
                                                        .name("test1")
                                                        .price(BigDecimal.ZERO)
                                                        .build();
        
        // When
        ProductDtoWithBrandAndCategory result = productService.updateProduct(productDto.getId(), updateProductRequest);

        // Then
        assertNotNull(result);
        assertEquals(productDto.getId(), result.getId());
        assertEquals("test1", result.getName());
        assertEquals(BigDecimal.ZERO, result.getPrice());
        assertEquals(brand2.getId(), result.getBrand().getId());
        assertEquals(category.getId(), result.getCategory().getId());
    }
}
