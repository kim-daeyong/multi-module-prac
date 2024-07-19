package com.musinsa.bo.api.domain.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.bo.api.domain.product.dto.request.CreateProductRequest;
import com.musinsa.bo.api.domain.product.dto.request.DeleteProductRequest;
import com.musinsa.bo.api.domain.product.dto.request.UpdateProductRequest;
import com.musinsa.bo.api.domain.product.service.ProductService;
import com.musinsa.core.common.message.ResponseMessage;
import com.musinsa.core.domain.product.dto.ProductDtoWithBrandAndCategory;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/api/bo/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    public ResponseMessage<ProductDtoWithBrandAndCategory> postMethodName(@RequestBody @Valid CreateProductRequest createProductRequest) {
        
        return new ResponseMessage<>(productService.createProduct(createProductRequest));
    }

    @PutMapping("/{id}")
    public ResponseMessage<ProductDtoWithBrandAndCategory> updateMethodName(@PathVariable Long id
        , @RequestBody @Valid UpdateProductRequest updateProductRequest) {
        
        return new ResponseMessage<>(productService.updateProduct(updateProductRequest));
    }

    @DeleteMapping("")
    public ResponseMessage<Void> deleteMethodName(@RequestBody @Valid DeleteProductRequest deleteProductRequest) {
        productService.deleteProduct(deleteProductRequest);

        return new ResponseMessage<>();
    }
}
