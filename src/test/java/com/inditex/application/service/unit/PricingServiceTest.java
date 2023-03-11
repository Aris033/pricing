package com.inditex.application.service.unit;

import com.inditex.application.repository.PricingRepository;
import com.inditex.application.service.PricingService;
import com.inditex.domain.Pricing;
import com.inditex.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PricingService.class})
@AutoConfigureTestDatabase
class PricingServiceTest {

    @InjectMocks
    private PricingService pricingService;
    @MockBean
    private PricingRepository pricingRepository;
    @Autowired
    private DataSource dataSource;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        pricingService = new PricingService(pricingRepository);
    }

    @Test
    @DisplayName("Test getValidPrice with valid date")
    void testGetValidPriceWithValidDate() throws NotFoundException {
        LocalDateTime validDate = LocalDateTime.parse("2020-06-14T16:00:00");
        int brandId = 1;
        int productId = 35455;

        Pricing pricing = createPricing(1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-06-30T00:00:00"), 1, 35455, 3, new BigDecimal("9.99"), "EUR");
        Pricing pricing2 = createPricing(1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-06-30T00:00:00"), 2, 35455, 5, new BigDecimal("19.99"), "EUR");
        List<Pricing> pricingList = new ArrayList<>();
        pricingList.add(pricing);
        pricingList.add(pricing2);

        when(pricingRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(pricingList);

        Pricing validPrice = pricingService.getValidPrice(validDate, brandId, productId);

        Assertions.assertEquals(pricing2, validPrice);
    }

    @Test
    @DisplayName("Test getValidPrice with invalid date")
    void testGetValidPriceWithInvalidDate() {
        LocalDateTime invalidDate = LocalDateTime.parse("2022-01-01T00:00:00");
        int brandId = 1;
        int productId = 35455;

        Pricing pricing = createPricing(1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-06-30T00:00:00"), 1, 35455, 3, new BigDecimal("9.99"), "EUR");
        Pricing pricing2 = createPricing(1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-06-30T00:00:00"), 2, 35455, 5, new BigDecimal("19.99"), "EUR");
        List<Pricing> pricingList = new ArrayList<>();
        pricingList.add(pricing);
        pricingList.add(pricing2);

        when(pricingRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(pricingList);

        Assertions.assertThrows(NotFoundException.class, () -> pricingService.getValidPrice(invalidDate, brandId, productId));
    }

    @Test
    @DisplayName("Test getValidPrice with null input")
    void testGetValidPriceWithNullInput() {
        LocalDateTime validDate = LocalDateTime.parse("2020-06-14T16:00:00");
        int brandId = 1;
        Integer productId = null;

        Assertions.assertThrows(NullPointerException.class, () -> pricingService.getValidPrice(validDate, brandId, productId));
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