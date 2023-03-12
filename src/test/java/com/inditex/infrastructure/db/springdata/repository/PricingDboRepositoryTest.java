package com.inditex.infrastructure.db.springdata.repository;

import static com.inditex.infrastructure.config.spring.SpringBootServiceTest.beforeAll;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.inditex.application.repository.PricingRepository;
import com.inditex.domain.Pricing;
import com.inditex.infrastructure.config.spring.TestRestTemplateConfig;
import com.inditex.infrastructure.db.springdata.dbo.PricesEntity;
import com.inditex.infrastructure.db.springdata.mapper.PriceEntityMapper;
import com.inditex.infrastructure.rest.spring.controllers.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PricingDboRepository.class})
@AutoConfigureTestDatabase
class PricingDboRepositoryTest {

    @InjectMocks
    PricingDboRepository pricingDboRepository;

    @MockBean
    private PricingRepository pricingRepository;

    @MockBean
    private PriceRepository priceRepository;

    @MockBean
    private PriceEntityMapper priceEntityMapper;

    @BeforeEach
    void setup() throws InterruptedException {
        MockitoAnnotations.initMocks(this);
        pricingDboRepository = new PricingDboRepository(priceRepository, priceEntityMapper);
        beforeAll();
    }

    @Test
    public void testFindByPriceList() {
        // Buscar precios por lista de precios
        Pricing pricing = pricingRepository.findByPriceList(1);

        // Comprobar que se ha encontrado el precio correcto
        assertThat(pricing).isNotNull();
        assertThat(pricing.getPriceList()).isEqualTo(1L);
        assertThat(pricing.getBrandId()).isEqualTo(1);
        assertThat(pricing.getProductId()).isEqualTo(35455);
        assertThat(pricing.getStartDate()).isEqualTo(LocalDateTime.parse("2020-06-14T00:00:00"));
        assertThat(pricing.getEndDate()).isEqualTo(LocalDateTime.parse("2020-12-31T23:59:59"));
        assertThat(pricing.getPrice()).isEqualTo(new BigDecimal("35.50"));
        assertThat(pricing.getCurr()).isEqualTo("EUR");
    }

    @Test
    public void testFindByBrandIdAndProductId() {

        // Buscar precios por marca e ID de producto
        List<Pricing> pricingList = pricingRepository.findByBrandIdAndProductId(1, 35455);

        // Comprobar que se han encontrado los precios correctos
        assertThat(pricingList.get(0)).isNotNull();
        assertThat(pricingList.get(0).getPriceList()).isEqualTo(0);
        assertThat(pricingList.get(0).getBrandId()).isEqualTo(1);
        assertThat(pricingList.get(0).getProductId()).isEqualTo(35455);
        assertThat(pricingList.get(0).getStartDate()).isEqualTo(LocalDateTime.parse("2020-06-14T00:00:00"));
        assertThat(pricingList.get(0).getEndDate()).isEqualTo(LocalDateTime.parse("2020-12-31T23:59:59"));
        assertThat(pricingList.get(0).getPrice()).isEqualTo(new BigDecimal("35.50"));
        assertThat(pricingList.get(0).getCurr()).isEqualTo("EUR");

    }
}

