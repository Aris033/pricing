package com.inditex.infrastructure.rest.spring.dto;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inditex.infrastructure.rest.spring.mapper.PricingMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CustomDtoTest {

    private PricingMapper mapper = Mappers.getMapper(PricingMapper.class);

    @Test
    public void testConstructorAndGetters() {
        //given
        int productId = 1;
        int brandId = 1;
        long priceList = 12345L;
        LocalDateTime startDate = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 1, 31, 23, 59);
        BigDecimal price = BigDecimal.valueOf(100.00);

        //when
        CustomDto customDto = new CustomDto(productId, brandId, priceList, startDate, endDate, price);

        //then
        assertEquals(productId, customDto.getProductId());
        assertEquals(brandId, customDto.getBrandId());
        assertEquals(priceList, customDto.getPriceList());
        assertEquals(startDate, customDto.getStartDate());
        assertEquals(endDate, customDto.getEndDate());
        assertEquals(price, customDto.getPrice());
    }

    @Test
    public void testSettersAndGetters() {
        //given
        CustomDto customDto = new CustomDto();
        int productId = 1;
        int brandId = 1;
        long priceList = 12345L;
        LocalDateTime startDate = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 1, 31, 23, 59);
        BigDecimal price = BigDecimal.valueOf(100.00);

        //when
        customDto.setProductId(productId);
        customDto.setBrandId(brandId);
        customDto.setPriceList(priceList);
        customDto.setStartDate(startDate);
        customDto.setEndDate(endDate);
        customDto.setPrice(price);

        //then
        assertEquals(productId, customDto.getProductId());
        assertEquals(brandId, customDto.getBrandId());
        assertEquals(priceList, customDto.getPriceList());
        assertEquals(startDate, customDto.getStartDate());
        assertEquals(endDate, customDto.getEndDate());
        assertEquals(price, customDto.getPrice());
    }

}
