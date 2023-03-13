package com.inditex.infrastructure.db.springdata.repository;

import com.inditex.infrastructure.db.springdata.dbo.PricesEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@EnableJpaRepositories(basePackages = "com.example.repository")
class PriceRepositoryTest {

    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        priceRepository = mock(PriceRepository.class);
    }

    @Test
    void testFindByBrandIdAndProductId() {
        List<PricesEntity> pricesEntities = new ArrayList<>();
        when(priceRepository.findByBrandIdAndProductId(1, 2)).thenReturn(pricesEntities);
        assertEquals(priceRepository.findByBrandIdAndProductId(1, 2), pricesEntities);
    }
}