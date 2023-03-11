package com.inditex.infrastructure.rest.spring.mapper;

import com.inditex.domain.Pricing;
import com.inditex.infrastructure.rest.spring.dto.CustomDto;
import com.inditex.infrastructure.rest.spring.dto.PriceDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricingMapper {

  PriceDto toDto (Pricing pricing);
  CustomDto toCustomDto (Pricing pricing);
  Pricing toDomain(PriceDto pricing);

}
