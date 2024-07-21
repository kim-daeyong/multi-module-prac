package com.musinsa.api.domain.cody.integration;

import com.musinsa.api.domain.cody.dto.response.AllCategoryMinPriceBrandResponse;
import com.musinsa.api.domain.cody.dto.response.MinAndMaxPriceProductByCategoryResponse;
import com.musinsa.api.domain.cody.dto.response.MinPriceProductsPerCategoryRespose;
import com.musinsa.api.domain.cody.service.CodyService;
import com.musinsa.core.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
public class CodyServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CodyService codyService;

    @Test
    void getMinPriceProductsPerCategoryTest() {
        // Given

        // When
        MinPriceProductsPerCategoryRespose response = codyService.getMinPriceProductsPerCategory();

        // Then
        assertEquals(8, response.getList().size());
        assertEquals(BigDecimal.valueOf(34100), response.getTotalPrice().setScale(0, RoundingMode.DOWN));
    }

    @Test
    void getAllCategoryMinPriceBrandTest() {
        // Given

        // When
        AllCategoryMinPriceBrandResponse response = codyService.getAllCategoryMinPriceBrand();

        // Then
        assertEquals("D", response.getBrand());
        assertEquals(8, response.getCategories().size());
        assertEquals(BigDecimal.valueOf(36100), response.getTotalPrice().setScale(0, RoundingMode.DOWN));
    }

    @Test
    void getMinAndMaxPriceProductByCategoryTest() {
        // Given

        // When
        MinAndMaxPriceProductByCategoryResponse response = codyService.getMinAndMaxPriceProductByCategory("상의");

        // Then
        assertEquals("상의", response.getCategory());
        assertEquals(BigDecimal.valueOf(10000), response.getMinPrice().getPrice().setScale(0, RoundingMode.DOWN));
        assertEquals("C", response.getMinPrice().getBrand());
        assertEquals(BigDecimal.valueOf(11400), response.getMaxPrice().getPrice().setScale(0, RoundingMode.DOWN));
        assertEquals("I", response.getMaxPrice().getBrand());
    }
}
