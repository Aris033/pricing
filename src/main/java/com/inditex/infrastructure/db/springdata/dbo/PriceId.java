package com.inditex.infrastructure.db.springdata.dbo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
public class PriceId implements Serializable {

    private int brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long priceList;
    private int productId;
    private int priority;

}
