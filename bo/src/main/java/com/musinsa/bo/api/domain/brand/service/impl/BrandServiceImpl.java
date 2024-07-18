package com.musinsa.bo.api.domain.brand.service.impl;

import org.springframework.stereotype.Service;

import com.musinsa.bo.api.domain.brand.service.BrandService;
import com.musinsa.core.common.exception.custom.DuplicateElementException;
import com.musinsa.core.common.message.ResponseCode;
import com.musinsa.core.domain.brand.dto.BrandDto;
import com.musinsa.core.domain.brand.dto.CreateBrand;
import com.musinsa.core.domain.brand.entity.Brand;
import com.musinsa.core.domain.brand.mapper.BrandMapper;
import com.musinsa.core.domain.brand.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandDto createBrand(CreateBrand createBrand) {
        Brand brand = Brand.of(createBrand);
        if (brandRepository.existsByName(createBrand.getName())) {
            throw new DuplicateElementException(ResponseCode.EXIST_BRAND_NAME_EXCEPTION);
        }

        return brandMapper.asDto(brandRepository.save(brand));
    }
    
}
