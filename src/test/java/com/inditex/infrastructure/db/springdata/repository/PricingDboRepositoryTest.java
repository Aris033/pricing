package com.inditex.infrastructure.db.springdata.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.inditex.application.repository.PricingRepository;
import com.inditex.domain.Pricing;
import com.inditex.infrastructure.db.springdata.dbo.PricesEntity;
import com.inditex.infrastructure.db.springdata.mapper.PriceEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class PricingDboRepositoryTest {
//
//    @Test
//    void testFindByPriceList() {
//        PricesEntity pricesEntity = mock(PricesEntity.class);
//        Pricing pricing = mock(Pricing.class);
//        when(priceRepository.findByPriceList(1L)).thenReturn(pricesEntity);
//        when(priceEntityMapper.toDomain(pricesEntity)).thenReturn(pricing);
//        assertEquals(pricingRepository.findByPriceList(1L), pricing);
//    }
//
//    @Test
//    void testFindByBrandIdAndProductId() {
//        List<PricesEntity> pricesEntities = new ArrayList<>();
//        List<Pricing> pricingList = new ArrayList<>();
//        when(priceRepository.findByBrandIdAndProductId(1, 2)).thenReturn(pricesEntities);
//        when(priceEntityMapper.toListDomain(pricesEntities)).thenReturn(pricingList);
//        assertEquals(pricingRepository.findByBrandIdAndProductId(1, 2), pricingList);
//    }
//
//    @Test
//    void testSave() {
//        Pricing pricing = mock(Pricing.class);
//        PricesEntity pricesEntity = mock(PricesEntity.class);
//        when(priceEntityMapper.toDbo(pricing)).thenReturn(pricesEntity);
//        when(priceRepository.save(pricesEntity)).thenReturn(pricesEntity);
//        when(priceEntityMapper.toDomain(pricesEntity)).thenReturn(pricing);
//        assertEquals(pricingRepository.save(pricing), pricing);
//    }
}
