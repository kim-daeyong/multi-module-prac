package com.musinsa.bo.doamin.product.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musinsa.bo.api.domain.product.dto.request.CreateProductRequest;
import com.musinsa.bo.api.domain.product.dto.request.DeleteProductRequest;
import com.musinsa.bo.api.domain.product.service.impl.ProductServiceImpl;
import com.musinsa.core.common.exception.custom.NotFoundException;
import com.musinsa.core.common.message.ResponseCode;
import com.musinsa.core.domain.brand.entity.Brand;
import com.musinsa.core.domain.brand.repository.BrandRepository;
import com.musinsa.core.domain.category.entity.Category;
import com.musinsa.core.domain.category.repository.CategoryRepository;
import com.musinsa.core.domain.product.dto.ProductDtoWithBrandAndCategory;
import com.musinsa.core.domain.product.entity.Product;
import com.musinsa.core.domain.product.mapper.ProductMapper;
import com.musinsa.core.domain.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductUnitTest {
    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProductTest() {
        // Given
        CreateProductRequest request = CreateProductRequest.builder().brandId(1l).categoryId(1l).build();
        Brand brand = Brand.builder().build();
        Category category = Category.builder().build();
        Product product = Product.builder().build();
        ProductDtoWithBrandAndCategory dto = ProductDtoWithBrandAndCategory.builder().build();

        when(brandRepository.findById(anyLong())).thenReturn(Optional.of(brand));
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.asDtoWithBrandAndCategory(any(Product.class))).thenReturn(dto);

        // When
        ProductDtoWithBrandAndCategory result = productService.createProduct(request);

        // Then
        assertNotNull(result);
        assertEquals(dto, result);
        verify(brandRepository, times(1)).findById(anyLong());
        verify(categoryRepository, times(1)).findById(anyLong());
        verify(productRepository, times(1)).save(any(Product.class));
        verify(productMapper, times(1)).asDtoWithBrandAndCategory(product);
    }

    @Test
    void createProductTest_not_exist_brand() {
        // Given
        CreateProductRequest request = CreateProductRequest.builder().brandId(1L).categoryId(1L).build();

        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        NotFoundException err = assertThrows(NotFoundException.class, () -> {
            productService.createProduct(request);
        });

        // Then
        assertEquals(ResponseCode.NOT_FOUND_BRAND_EXCEPTION.getMessage(), err.getMessage());

        verify(brandRepository).findById(1L);
        verify(categoryRepository, never()).findById(anyLong());
        verify(productRepository, never()).save(any(Product.class));
        verify(productMapper, never()).asDtoWithBrandAndCategory(any(Product.class));
    }

    @Test
    void createProductTest_not_exist_category() {
        // Given
        CreateProductRequest request = CreateProductRequest.builder().brandId(1L).categoryId(2L).build();
        Brand brand = Brand.builder().build();
        Category category = Category.builder().build();

        when(brandRepository.findById(anyLong())).thenReturn(Optional.of(brand));
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

        // When
        NotFoundException err = assertThrows(NotFoundException.class, () -> {
            productService.createProduct(request);
        });

        // Then
        assertEquals(ResponseCode.NOT_FOUND_CATEGORY_EXCEPTION.getMessage(), err.getMessage());

        verify(brandRepository).findById(1L);
        verify(categoryRepository).findById(2L);
        verify(productRepository, never()).save(any(Product.class));
        verify(productMapper, never()).asDtoWithBrandAndCategory(any(Product.class));
    }

    @Test
    void deleteProductTest() {
        // Given
        DeleteProductRequest deleteProductRequest = DeleteProductRequest.builder()
                                                        .ids(List.of(1l))
                                                        .build();

        // When
        productService.deleteProduct(deleteProductRequest);

        // Then
        verify(productRepository, times(1)).deleteAllByIdInBatch(deleteProductRequest.getIds());
    }
    
}
