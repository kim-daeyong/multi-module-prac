package com.musinsa.core.domain.product.repository.querydsl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.musinsa.core.domain.product.dto.MinPriceProductPerCategory;
import com.musinsa.core.domain.product.entity.Product;

@Repository
public interface ProductQuerydslRepository {
    Optional<Product> findAllByIdWithBrandAndCategory(Long id);

    List<MinPriceProductPerCategory> getMinPriceProductPerCategory();
}
