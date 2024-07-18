package com.musinsa.bo.doamin.brand.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musinsa.bo.api.domain.brand.service.impl.BrandServiceImpl;
import com.musinsa.core.domain.brand.dto.BrandDto;
import com.musinsa.core.domain.brand.dto.CreateBrand;
import com.musinsa.core.domain.brand.entity.Brand;
import com.musinsa.core.domain.brand.mapper.BrandMapper;
import com.musinsa.core.domain.brand.repository.BrandRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BrandUnitTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    void createBrandTest_return_BrandDto() {
        // Given
        CreateBrand createBrand = CreateBrand.builder().name("test").build();
        Brand brand = Brand.builder().id(1l).name("test").build();
        BrandDto brandDto = BrandDto.builder().id(1l).name("test").build();

        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        when(brandMapper.asDto(any(Brand.class))).thenReturn(brandDto);

        // When
        BrandDto result = brandService.createBrand(createBrand);

        // Then
        assertNotNull(result);
        assertEquals(brandDto, result);
        verify(brandRepository, times(1)).save(any(Brand.class));
        verify(brandMapper, times(1)).asDto(brand);
    }
}

