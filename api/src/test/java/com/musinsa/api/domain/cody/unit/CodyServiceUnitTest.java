package com.musinsa.api.domain.cody.unit;

import com.musinsa.api.domain.cody.dto.response.AllCategoryMinPriceBrandResponse;
import com.musinsa.api.domain.cody.dto.response.MinAndMaxPriceProductByCategoryResponse;
import com.musinsa.api.domain.cody.dto.response.MinPriceProductsPerCategoryRespose;
import com.musinsa.api.domain.cody.service.impl.CodyServiceImpl;
import com.musinsa.core.domain.product.dto.CategoryMinPriceResult;
import com.musinsa.core.domain.product.dto.MinAndMaxPriceByCategoryResult;
import com.musinsa.core.domain.product.dto.MinAndMaxPriceProductByCategory;
import com.musinsa.core.domain.product.dto.MinPriceProductPerCategory;
import com.musinsa.core.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.musinsa.core.common.constant.CodyConstant.ALL_CATEGORY_FOR_TARGET;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CodyServiceUnitTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CodyServiceImpl codyService;

    @Test
    void getMinPriceProductsPerCategoryTest() {
        // Given
        List<MinPriceProductPerCategory> mockResultList = List.of(
                MinPriceProductPerCategory.builder().price(BigDecimal.ONE).build(),
                MinPriceProductPerCategory.builder().price(BigDecimal.ONE).build()
        );

        when(productRepository.getMinPriceProductPerCategory()).thenReturn(mockResultList);

        // When
        MinPriceProductsPerCategoryRespose result = codyService.getMinPriceProductsPerCategory();

        // Then
        assertNotNull(result);
        assertEquals(mockResultList, result.getList());
        assertEquals(BigDecimal.TWO, result.getTotalPrice());
        verify(productRepository, times(1)).getMinPriceProductPerCategory();
    }

    @Test
    void getAllCategoryMinPriceBrandTest() {
        // Given
        List<CategoryMinPriceResult> mockResultList = List.of(
                CategoryMinPriceResult.builder().price(BigDecimal.ONE).build(),
                CategoryMinPriceResult.builder().price(BigDecimal.ONE).build()
        );

        when(productRepository.getAllCategoryMinPriceBrand(ALL_CATEGORY_FOR_TARGET)).thenReturn(mockResultList);

        // When
        AllCategoryMinPriceBrandResponse result = codyService.getAllCategoryMinPriceBrand();

        // Then
        assertNotNull(result);
        assertEquals(BigDecimal.TWO, result.getTotalPrice());
        verify(productRepository, times(1)).getAllCategoryMinPriceBrand(ALL_CATEGORY_FOR_TARGET);
    }

    @Test
    void getMinAndMaxPriceProductByCategoryTest() {
        // Given
        String categoryName = "test";
        MinAndMaxPriceByCategoryResult mockResult = MinAndMaxPriceByCategoryResult.builder()
                .minPrice(MinAndMaxPriceProductByCategory.builder()
                        .price(BigDecimal.ONE)
                        .brand("test")
                        .build())
                .maxPrice(MinAndMaxPriceProductByCategory.builder()
                        .price(BigDecimal.TWO)
                        .brand("test")
                        .build())
                .build();

        when(productRepository.findMinMaxPriceByCategory(categoryName)).thenReturn(mockResult);

        // When
        MinAndMaxPriceProductByCategoryResponse result = codyService.getMinAndMaxPriceProductByCategory(categoryName);

        // Then
        assertNotNull(result);
        assertEquals(BigDecimal.ONE, result.getMinPrice().getPrice());
        assertEquals(BigDecimal.TWO, result.getMaxPrice().getPrice());
        verify(productRepository, times(1)).findMinMaxPriceByCategory(categoryName);
    }
}
