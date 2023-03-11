package com.inditex.infrastructure.db.springdata.dbo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BRAND")
public class BrandEntity {
    @Id
    @Column(name = "BRAND_ID")
    private int brandId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<PricesEntity> prices;
}
