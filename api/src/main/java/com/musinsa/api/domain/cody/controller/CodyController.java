package com.musinsa.api.domain.cody.controller;

import com.musinsa.api.domain.cody.dto.response.MinAndMaxPriceProductByCategoryResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.musinsa.api.domain.cody.dto.response.MinPriceProductsPerCategoryRespose;
import com.musinsa.api.domain.cody.service.CodyService;
import com.musinsa.core.common.message.ResponseMessage;

import lombok.RequiredArgsConstructor;


@RequestMapping("/api/cody")
@RestController
@RequiredArgsConstructor
public class CodyController {
    private final CodyService codyService;

    @GetMapping("")
    public ResponseMessage<MinPriceProductsPerCategoryRespose> getMinPriceProductsPerCategory() {
        return new ResponseMessage<>(codyService.getMinPriceProductsPerCategory());
    }

    @GetMapping("/category")
    public ResponseMessage<MinAndMaxPriceProductByCategoryResponse> getMinAndMaxPriceProductByCategory(@RequestParam @Valid String categoryName) {
        return new ResponseMessage<>(codyService.getMinAndMaxPriceProductByCategory(categoryName));
    }
}
