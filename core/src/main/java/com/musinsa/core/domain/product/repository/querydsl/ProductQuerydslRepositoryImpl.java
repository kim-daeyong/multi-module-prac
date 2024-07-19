package com.musinsa.core.domain.product.repository.querydsl;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import static com.musinsa.core.domain.product.entity.QProduct.product;
import static com.musinsa.core.domain.brand.entity.QBrand.brand;
import static com.musinsa.core.domain.category.entity.QCategory.category;

import com.musinsa.core.domain.product.entity.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ProductQuerydslRepositoryImpl extends QuerydslRepositorySupport implements ProductQuerydslRepository {
    private final JPAQueryFactory factory;

    public ProductQuerydslRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Product.class);
        this.factory = jpaQueryFactory;
    }

    @Override
    public Optional<Product> findAllByIdWithBrandAndCategory(Long productId) {
        return Optional.ofNullable(from(product)
                .join(product.brand, brand).fetchJoin()
                .join(product.category, category).fetchJoin()
                .where(product.id.eq(productId))
                .fetchOne());
    }

}
