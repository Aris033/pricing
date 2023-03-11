package com.inditex.application.service.integration;

import com.inditex.application.repository.PricingRepository;
import com.inditex.application.service.PricingService;
import com.inditex.domain.Pricing;
import com.inditex.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PricingService.class})
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
class PricingServiceIntegrationTest {
    @InjectMocks
    private PricingService pricingService;
    @MockBean
    private PricingRepository pricingRepository;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        pricingService = new PricingService(pricingRepository);
    }
    @Test
    void should_return_valid_price() throws Exception {
        // Given
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        int brandId = 1;
        int productId = 35455;
        List<Pricing> pricings = Arrays.asList(
                createPricing(1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-06-30T00:00:00"), 1, 35455, 1, new BigDecimal("10.00"), "EUR"),
                createPricing(1, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), 2, 35455, 2, new BigDecimal("15.00"), "EUR"),
                createPricing(1, LocalDateTime.parse("2020-06-15T00:00:00"), LocalDateTime.parse("2020-06-15T11:00:00"), 3, 35455, 3, new BigDecimal("20.00"), "EUR"),
                createPricing(1, LocalDateTime.parse("2020-06-15T16:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 4, 35455, 4, new BigDecimal("25.00"), "EUR")
        );
        when(pricingRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(pricings);

        // When
        Pricing result = pricingService.getValidPrice(date, brandId, productId);

        // Then
        Pricing expected = createPricing(1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-06-30T00:00:00"), 1, 35455, 1, new BigDecimal("10.00"), "EUR");
        assertEquals(expected, result);
    }

    @Test
    void should_throw_not_found_exception_when_no_valid_price_found() {
        // Given
        LocalDateTime date = LocalDateTime.parse("2021-06-14T10:00:00");
        int brandId = 1;
        int productId = 35455;
        List<Pricing> pricings = Arrays.asList(
                createPricing(1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-06-30T00:00:00"), 1, 35455, 1, new BigDecimal("10.00"), "EUR"),
                createPricing(1, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), 2, 35455, 2, new BigDecimal("15.00"), "EUR")
        );
        when(pricingRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(pricings);

        // When and Then
        assertThrows(NotFoundException.class, () -> pricingService.getValidPrice(date, brandId, productId));
    }

    private Pricing createPricing(int brandId, LocalDateTime startDate, LocalDateTime endDate, int priceList, int productId, int priority, BigDecimal price, String curr) {
        Pricing pricing = new Pricing();
        pricing.setBrandId(brandId);
        pricing.setStartDate(startDate);
        pricing.setEndDate(endDate);
        pricing.setPriceList(priceList);
        pricing.setProductId(productId);
        pricing.setPriority(priority);
        pricing.setPrice(price);
        pricing.setCurr(curr);
        return pricing;
    }
}