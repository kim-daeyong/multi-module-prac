package com.musinsa.core.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.core.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
