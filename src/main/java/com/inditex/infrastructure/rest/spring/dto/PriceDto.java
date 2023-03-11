package com.inditex.infrastructure.rest.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {
    private int brandId;
    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
    private LocalDateTime endDate;
    private long priceList;
    private int productId;
    private int priority;
    private BigDecimal price;
    private String curr;
}
