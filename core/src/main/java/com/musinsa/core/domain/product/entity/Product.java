package com.musinsa.core.domain.product.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.Comment;

import com.musinsa.core.domain.BaseEntity;
import com.musinsa.core.domain.brand.entity.Brand;
import com.musinsa.core.domain.category.entity.Category;
import com.musinsa.core.domain.product.dto.CreateProduct;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Index;
import lombok.*;

@Comment(value = "상품")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "product", indexes = {
    @Index(name = "idx_brand_id", columnList = "brand_id"),
    @Index(name = "idx_category_id", columnList = "category_id"),
    @Index(name = "idx_price", columnList = "price")
})
@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "id")
    private Integer id;

    @Comment(value = "상품 이름")
    @Column(nullable = false)
    private String name;

    @Comment(value = "브랜드 id")
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Comment(value = "카테고리 id")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Comment(value = "가격")
    @Column(nullable = false)
    private BigDecimal price;

    public static Product of(CreateProduct createProduct) {
        return Product.builder()
            .name(createProduct.getName())
            .price(createProduct.getPrice())
            .brand(createProduct.getBrand())
            .category(createProduct.getCategory())
            .build();
    }
    
}
