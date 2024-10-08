package com.musinsa.core.domain.category.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;

import com.musinsa.core.domain.BaseEntity;
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

@Comment(value = "카테고리")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name= "category")
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "id")
    private Long id;

    @Comment(value = "카테고리 이름")
    @Column(nullable = false, unique = true)
    private String name;
    
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    List<Product> products = new ArrayList<>();
}
