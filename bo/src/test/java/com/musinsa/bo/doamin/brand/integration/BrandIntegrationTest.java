package com.musinsa.bo.doamin.brand.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import com.musinsa.bo.api.domain.brand.service.BrandService;
import com.musinsa.core.common.exception.custom.DuplicateElementException;
import com.musinsa.core.common.message.ResponseCode;
import com.musinsa.core.domain.brand.dto.BrandDto;
import com.musinsa.core.domain.brand.dto.CreateBrand;
import com.musinsa.core.domain.brand.entity.Brand;
import com.musinsa.core.domain.brand.repository.BrandRepository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@ContextConfiguration(classes = {TestRedisConfig.class, TestEmbeddedRedisConfig.class})
@TestPropertySource(properties = {
    "spring.sql.init.mode=never"
})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
public class BrandIntegrationTest {
    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandRepository brandRepository;

    @AfterEach
    public void tearDown() {
        brandRepository.deleteAll();
    }

    @Test
    void createBrandTest() {
        // Given
        CreateBrand createBrand = CreateBrand.builder()
                                    .name("test")
                                    .build();
   
        // When
        BrandDto result = brandService.createBrand(createBrand);

        // Then
        assertNotNull(result);
        assertEquals("test", result.getName());

        Brand brand = brandRepository.findById(result.getId()).orElse(null);
        assertNotNull(brand);
        assertEquals("test", brand.getName());
    }

    @Test
    void createBrandTest_duplicatite() {
        // Given
        CreateBrand createBrand = CreateBrand.builder()
                                    .name("test")
                                    .build();

        brandService.createBrand(createBrand);
   
        // When
        // Then
        assertThatThrownBy(() -> {
            brandService.createBrand(createBrand);
        }).isInstanceOf(DuplicateElementException.class)
                .hasMessageContaining(ResponseCode.EXIST_BRAND_NAME_EXCEPTION.getMessage());
    }
}
