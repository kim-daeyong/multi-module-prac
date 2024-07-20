package com.musinsa.api.domain.cody.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.api.domain.cody.dto.response.MinPriceProductsPerCategoryRespose;
import com.musinsa.api.domain.cody.service.CodyService;
import com.musinsa.core.common.message.ResponseMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RequestMapping("/api/cody")
@RestController
@RequiredArgsConstructor
public class CodyController {
    private final CodyService codyService;

    @GetMapping("")
    public ResponseMessage<MinPriceProductsPerCategoryRespose> getMinPriceProductsPerCategory() {
        return new ResponseMessage<>(codyService.getMinPriceProductsPerCategory());
    }
}
