package com.inditex.infrastructure.db.springdata.dbo;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(PriceId.class)
@Table(name = "PRICES")
public class PricesEntity {
    @Id
    @Column(name = "BRAND_ID")
    private int brandId;

    @Id
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Id
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Id
    @Column(name = "PRICE_LIST")
    private long priceList;

    @Id
    @Column(name = "PRODUCT_ID")
    private int productId;

    @Id
    @Column(name = "PRIORITY")
    private int priority;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CURR")
    private String curr;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "BRAND_ID", insertable = false, updatable = false)
    private BrandEntity brand;


}
