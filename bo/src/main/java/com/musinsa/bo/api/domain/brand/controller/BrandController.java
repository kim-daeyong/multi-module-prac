package com.musinsa.bo.api.domain.brand.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.bo.api.domain.brand.dto.request.CreateBrandRequest;
import com.musinsa.bo.api.domain.brand.service.BrandService;
import com.musinsa.core.common.message.ResponseMessage;
import com.musinsa.core.domain.brand.dto.BrandDto;
import com.musinsa.core.domain.brand.dto.CreateBrand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/api/bo/brand")
@RestController
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("")
    public ResponseMessage<BrandDto> createBrand(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        
        return new ResponseMessage<>(brandService.createBrand(CreateBrand.builder()
                                                                            .name(createBrandRequest.getName())
                                                                            .build()));
    }
}
