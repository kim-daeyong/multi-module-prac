package com.musinsa.core.domain.product.repository.querydsl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import static com.musinsa.core.domain.product.entity.QProduct.product;
import static com.musinsa.core.domain.brand.entity.QBrand.brand;
import static com.musinsa.core.domain.category.entity.QCategory.category;

import com.musinsa.core.domain.product.dto.MinPriceProductPerCategory;
import com.musinsa.core.domain.product.entity.Product;
import com.musinsa.core.domain.product.entity.QProduct;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
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

    @Override
    public List<MinPriceProductPerCategory> getMinPriceProductPerCategory() {
        QProduct subProduct = new QProduct("sp");

        var subQuery = JPAExpressions
            .select(
                subProduct.category.id
                , subProduct.price.min())
            .from(subProduct)
            .groupBy(subProduct.category.id);

        List<MinPriceProductPerCategory> result = factory
            .select(
                Projections.constructor(
                    MinPriceProductPerCategory.class,
                    category.name,
                    brand.name,
                    product.price
                )
            )
            .from(product)
            .join(product.category, category)
            .join(product.brand, brand)
            .where(
                Expressions.list(product.category.id, product.price)
                    .in(subQuery)
            )
            .groupBy(category.name, brand.name)
            .fetch();
    
        return result.stream()
            .collect(Collectors.toMap(
                    MinPriceProductPerCategory::getCategory,
                    product -> product,
                    (product1, product2) -> product1.getPrice().compareTo(product2.getPrice()) <= 0 ? product1 : product2
            ))
            .values()
            .stream()
            .collect(Collectors.toList());
    }
}
