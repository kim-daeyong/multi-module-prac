package com.musinsa.core.domain.brand.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.musinsa.core.domain.brand.dto.BrandDto;
import com.musinsa.core.domain.brand.entity.Brand;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {

    BrandDto asDto(Brand dto);
    
}
