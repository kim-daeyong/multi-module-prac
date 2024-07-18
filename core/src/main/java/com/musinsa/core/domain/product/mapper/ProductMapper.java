package com.musinsa.core.domain.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.musinsa.core.domain.brand.dto.BrandDto;
import com.musinsa.core.domain.brand.entity.Brand;
import com.musinsa.core.domain.category.dto.CategoryDto;
import com.musinsa.core.domain.category.entity.Category;
import com.musinsa.core.domain.product.dto.ProductDtoWithBrandAndCategory;
import com.musinsa.core.domain.product.entity.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "brand", source = "brand", qualifiedByName = "brandToBrandDto")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryToCategoryDto")
    ProductDtoWithBrandAndCategory asDtoWithBrandAndCategory(Product product);

    @Named("brandToBrandDto")
    BrandDto brandToBrandDto(Brand brand);
    
    @Named("categoryToCategoryDto")
    CategoryDto categoryToCategoryDto(Category category);


}
