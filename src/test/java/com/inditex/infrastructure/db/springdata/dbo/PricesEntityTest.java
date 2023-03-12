package com.inditex.infrastructure.db.springdata.dbo;

import com.inditex.domain.Pricing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Pricing.class})
@AutoConfigureTestDatabase
class PricesEntityTest {

    @Mock
    private BrandEntity brand;

    @Test
    void gettersAndSettersTest() {
        PricesEntity pricesEntity = new PricesEntity();

        pricesEntity.setBrandId(1);
        pricesEntity.setStartDate(LocalDateTime.of(2022, 1, 1, 0, 0));
        pricesEntity.setEndDate(LocalDateTime.of(2022, 6, 30, 23, 59, 59));
        pricesEntity.setPriceList(1);
        pricesEntity.setProductId(11111);
        pricesEntity.setPriority(0);
        pricesEntity.setPrice(new BigDecimal("35.50"));
        pricesEntity.setCurr("EUR");
        pricesEntity.setBrand(brand);

        assertEquals(1, pricesEntity.getBrandId());
        assertEquals(LocalDateTime.of(2022, 1, 1, 0, 0), pricesEntity.getStartDate());
        assertEquals(LocalDateTime.of(2022, 6, 30, 23, 59, 59), pricesEntity.getEndDate());
        assertEquals(1, pricesEntity.getPriceList());
        assertEquals(11111, pricesEntity.getProductId());
        assertEquals(0, pricesEntity.getPriority());
        assertEquals(new BigDecimal("35.50"), pricesEntity.getPrice());
        assertEquals("EUR", pricesEntity.getCurr());
        assertEquals(brand, pricesEntity.getBrand());
    }

}
