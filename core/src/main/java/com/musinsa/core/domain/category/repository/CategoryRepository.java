package com.musinsa.core.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.core.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
