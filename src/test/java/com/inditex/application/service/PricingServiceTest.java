package com.inditex.application.service;

import com.inditex.application.repository.PricingRepository;
import com.inditex.application.service.PricingService;
import com.inditex.domain.Pricing;
import com.inditex.exception.NotFoundException;
import com.inditex.infrastructure.config.spring.SpringBootService;
import com.inditex.infrastructure.db.springdata.dbo.PricesEntity;
import com.inditex.utils.UtilsTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.inditex.infrastructure.config.spring.SpringBootServiceTest.beforeAll;
import static com.inditex.utils.UtilsTest.createPricing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PricingService.class})
@AutoConfigureTestDatabase
class PricingServiceTest {

    @InjectMocks
    private PricingService pricingService;

    @MockBean
    private PricingRepository pricingRepository;

    @BeforeEach
    void setup() throws InterruptedException {
        MockitoAnnotations.initMocks(this);
        pricingService = new PricingService(pricingRepository);
        beforeAll();
    }

    @Test
    public void shouldRetrievePriceWithMockRepository() {
        List<Pricing> pricingList = UtilsTest.createPricingList(2);
        LocalDateTime invalidDate = LocalDateTime.parse("2020-06-14T10:00:00");
        when(pricingRepository.findByBrandIdAndProductId(1, 2)).thenReturn(pricingList);
        Pricing validPrice = pricingService.getValidPrice(invalidDate, 1, 2);
        assertEquals(validPrice.getPriority(), 1);
    }

    @Test
    @DisplayName("Test getValidPrice with invalid date")
    void shouldThrowNotFoundExceptionWithInvalidDate() {
        LocalDateTime invalidDate = LocalDateTime.parse("2022-06-14T10:00:00");
        int brandId = 1;
        int productId = 35455;
        List<Pricing> pricings = UtilsTest.createPricingList(3);
        when(pricingRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(pricings);
        assertThrows(NotFoundException.class, () -> pricingService.getValidPrice(invalidDate, brandId, productId));
    }

    @Test
    @DisplayName("Test getValidPrice with null input")
    void shouldThrowNullPointerExceptionWithNullInput() {
        LocalDateTime validDate = LocalDateTime.parse("2020-06-14T16:00:00");
        int brandId = 1;
        Integer productId = null;
        assertThrows(NullPointerException.class, () -> pricingService.getValidPrice(validDate, brandId, productId));
    }

    @Test
    void shouldReturnValidPrice() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        int brandId = 1;
        int productId = 35455;
        List<Pricing> pricings = UtilsTest.createPricingList(3);
        when(pricingRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(pricings);
        Pricing result = pricingService.getValidPrice(date, brandId, productId);
        Pricing expected = UtilsTest.createPricing();
        Assertions.assertEquals(expected, result);
    }

}
