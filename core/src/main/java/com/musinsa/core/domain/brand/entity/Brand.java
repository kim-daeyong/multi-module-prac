package com.musinsa.core.domain.brand.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;

import com.musinsa.core.domain.BaseEntity;
import com.musinsa.core.domain.brand.dto.CreateBrand;
import com.musinsa.core.domain.product.entity.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Comment(value = "브랜드")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name= "brand")
@Entity
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "id")
    private Long id;

    @Comment(value = "브랜드 이름")
    @Column(nullable = false, unique = true)
    private String name;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    List<Product> products = new ArrayList<>();

    public static Brand of(CreateBrand createBrand) {
        return Brand.builder()
            .name(createBrand.getName())
            .build();
    }
}
