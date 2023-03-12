package com.inditex.infrastructure.db.springdata.mapper;

import com.inditex.domain.Pricing;
import com.inditex.infrastructure.db.springdata.dbo.PricesEntity;
import com.inditex.utils.UtilsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class PriceEntityMapperTest {
    private PriceEntityMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(PriceEntityMapper.class);
    }

    @Test
    void testToDomain() {
        PricesEntity pricesEntity = mock(PricesEntity.class);
        Pricing pricing = mapper.toDomain(pricesEntity);
        assertEquals(pricing.getClass(), Pricing.class);
    }

    @Test
    void testToListDomain() {
        List<PricesEntity> pricesEntities = new ArrayList<>();
        pricesEntities.add(mock(PricesEntity.class));
        List<Pricing> pricingList = mapper.toListDomain(pricesEntities);
        assertEquals(pricingList.getClass(), ArrayList.class);
        assertEquals(pricingList.get(0).getClass(), Pricing.class);
    }

    @Test
    void testToDbo() {
        Pricing pricing = mock(Pricing.class);
        PricesEntity pricesEntity = mapper.toDbo(pricing);
        assertEquals(pricesEntity.getClass(), PricesEntity.class);
    }
}