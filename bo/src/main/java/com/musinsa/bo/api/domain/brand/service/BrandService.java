package com.musinsa.bo.api.domain.brand.service;

import com.musinsa.core.domain.brand.dto.BrandDto;
import com.musinsa.core.domain.brand.dto.CreateBrand;

public interface BrandService {

    BrandDto createBrand(CreateBrand createBrand);
    
}
