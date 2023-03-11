package com.inditex.infrastructure.db.springdata.mapper;

import com.inditex.domain.Pricing;
import com.inditex.infrastructure.db.springdata.dbo.PricesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

  Pricing toDomain(PricesEntity pricesEntity);
  List<Pricing> toListDomain(List<PricesEntity> pricesEntity);
  PricesEntity toDbo(Pricing pricing);

}
