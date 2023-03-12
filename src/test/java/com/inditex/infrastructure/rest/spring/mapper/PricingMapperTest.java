package com.inditex.infrastructure.rest.spring.mapper;

import com.inditex.domain.Pricing;
import com.inditex.infrastructure.rest.spring.dto.CustomDto;
import com.inditex.infrastructure.rest.spring.dto.PriceDto;
import com.inditex.utils.UtilsTest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PricingMapperTest {

    private PricingMapper mapper = Mappers.getMapper(PricingMapper.class);

    @Test
    public void testToDto() {
        Pricing pricing = UtilsTest.createPricing();

        PriceDto priceDto = mapper.toDto(pricing);

        assertEquals(1, priceDto.getBrandId());
        assertEquals(UtilsTest.START, priceDto.getStartDate());
        assertEquals(UtilsTest.END, priceDto.getEndDate());
        assertEquals(12345L, priceDto.getPriceList());
        assertEquals(35455, priceDto.getProductId());
        assertEquals(1, priceDto.getPriority());
        assertEquals(new BigDecimal("99.99"), priceDto.getPrice());
        assertEquals("USD", priceDto.getCurr());
    }

    @Test
    public void testToCustomDto() {
        Pricing pricing = UtilsTest.createPricing();

        CustomDto customDto = mapper.toCustomDto(pricing);

        assertEquals(35455, customDto.getProductId());
        assertEquals(1, customDto.getBrandId());
        assertEquals(12345L, customDto.getPriceList());
        assertEquals(UtilsTest.START, customDto.getStartDate());
        assertEquals(UtilsTest.END, customDto.getEndDate());
        assertEquals(new BigDecimal("99.99"), customDto.getPrice());
    }

    @Test
    public void testToDomain() {
        //given
        PriceDto priceDto = new PriceDto();
        priceDto.setBrandId(1);
        priceDto.setStartDate(UtilsTest.START);
        priceDto.setEndDate(UtilsTest.END);
        priceDto.setPriceList(12345L);
        priceDto.setProductId(35455);
        priceDto.setPriority(1);
        priceDto.setPrice(new BigDecimal("99.99"));
        priceDto.setCurr("USD");

        Pricing pricing = mapper.toDomain(priceDto);

        assertEquals(1, pricing.getBrandId());
        assertEquals(UtilsTest.START, pricing.getStartDate());
        assertEquals(UtilsTest.END, pricing.getEndDate());
        assertEquals(12345L, pricing.getPriceList());
        assertEquals(35455, pricing.getProductId());
        assertEquals(1, pricing.getPriority());
        assertEquals(new BigDecimal("99.99"), pricing.getPrice());
        assertEquals("USD", pricing.getCurr());
    }

    @Test
    public void testToCustomDtoWithNull() {
        Pricing pricing = null;
        CustomDto customDto = mapper.toCustomDto(pricing);
        assertNull(customDto);
    }

    @Test
    public void testToDomainWithNull() {
        PriceDto priceDto = null;
        Pricing pricing = mapper.toDomain(priceDto);
        assertNull(pricing);
    }

}

