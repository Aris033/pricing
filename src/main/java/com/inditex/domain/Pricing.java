package com.inditex.domain;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Pricing {

    private int brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long priceList;
    private int productId;
    private int priority;
    private BigDecimal price;
    private String curr;
    public Pricing () {

    }
}
