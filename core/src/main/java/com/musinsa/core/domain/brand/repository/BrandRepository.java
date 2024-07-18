package com.musinsa.core.domain.brand.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.core.domain.brand.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Boolean existsByName(String name);
}
