package com.musinsa.bo.api.domain.brand.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateBrandRequest {
    
    @NotBlank(message = "브랜드명은 공백일 수 없습니다.")
    private String name;   
}
