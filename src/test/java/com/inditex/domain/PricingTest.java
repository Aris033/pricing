package com.inditex.domain;

import com.inditex.application.repository.PricingRepository;
import com.inditex.application.service.PricingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Pricing.class})
@AutoConfigureTestDatabase
class PricingTest {

    @InjectMocks
    private PricingService pricingService;

    @MockBean
    private PricingRepository pricingRepository;

    @BeforeEach
    void setup() throws InterruptedException {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testGetterAndSetter() {
        Pricing pricing = new Pricing();
        pricing.setBrandId(1);
        pricing.setStartDate(LocalDateTime.of(2023, 3, 12, 10, 0));
        pricing.setEndDate(LocalDateTime.of(2023, 3, 13, 10, 0));
        pricing.setPriceList(1);
        pricing.setProductId(2);
        pricing.setPriority(3);
        pricing.setPrice(BigDecimal.valueOf(99.99));
        pricing.setCurr("USD");

        Assertions.assertEquals(1, pricing.getBrandId());
        Assertions.assertEquals(LocalDateTime.of(2023, 3, 12, 10, 0), pricing.getStartDate());
        Assertions.assertEquals(LocalDateTime.of(2023, 3, 13, 10, 0), pricing.getEndDate());
        Assertions.assertEquals(1, pricing.getPriceList());
        Assertions.assertEquals(2, pricing.getProductId());
        Assertions.assertEquals(3, pricing.getPriority());
        Assertions.assertEquals(BigDecimal.valueOf(99.99), pricing.getPrice());
        Assertions.assertEquals("USD", pricing.getCurr());
    }

    @Test
    void testEqualsAndHashCode() {
        Pricing pricing1 = new Pricing(1, LocalDateTime.of(2023, 3, 12, 10, 0),
                LocalDateTime.of(2023, 3, 13, 10, 0), 1, 2, 3, BigDecimal.valueOf(99.99), "USD");
        Pricing pricing2 = new Pricing(1, LocalDateTime.of(2023, 3, 12, 10, 0),
                LocalDateTime.of(2023, 3, 13, 10, 0), 1, 2, 3, BigDecimal.valueOf(99.99), "USD");

        Assertions.assertEquals(pricing1, pricing2);
        Assertions.assertEquals(pricing1.hashCode(), pricing2.hashCode());
    }

    @Test
    void testAllArgsConstructor() {
        Pricing pricing = new Pricing(1, LocalDateTime.of(2023, 3, 12, 10, 0),
                LocalDateTime.of(2023, 3, 13, 10, 0), 1, 2, 3, BigDecimal.valueOf(99.99), "USD");

        Assertions.assertEquals(1, pricing.getBrandId());
        Assertions.assertEquals(LocalDateTime.of(2023, 3, 12, 10, 0), pricing.getStartDate());
        Assertions.assertEquals(LocalDateTime.of(2023, 3, 13, 10, 0), pricing.getEndDate());
        Assertions.assertEquals(1, pricing.getPriceList());
        Assertions.assertEquals(2, pricing.getProductId());
        Assertions.assertEquals(3, pricing.getPriority());
        Assertions.assertEquals(BigDecimal.valueOf(99.99), pricing.getPrice());
        Assertions.assertEquals("USD", pricing.getCurr());
    }
}
