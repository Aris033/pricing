package com.inditex.infrastructure.rest.spring.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.application.repository.PricingRepository;
import com.inditex.application.service.PricingService;
import com.inditex.domain.Pricing;
import com.inditex.infrastructure.config.spring.DateTimeConfig;
import com.inditex.infrastructure.rest.spring.Resources;
import com.inditex.infrastructure.rest.spring.mapper.PricingMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PricingService.class, Resources.class})
@ComponentScan(basePackages = "com.inditex.infrastructure.rest.spring.mapper")
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Import(DateTimeConfig.class)
class ResourcesTest {

    @InjectMocks
    private Resources resources;
    @Mock
    private PricingService pricingService;
    @Mock
    private PricingMapper pricingMapper;
    @Mock
    private Pricing pricing;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PricingRepository pricingRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        pricingService = new PricingService(pricingRepository);
    }
//
//    @Test
//    void should_return_valid_price() throws Exception {
//        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
//        int brandId = 1;
//        int productId = 35455;
//        // Creamos el objeto Pricing que se espera que se devuelva
//        Pricing pricing = createPricing(1, LocalDateTime.parse("2020-06-13T00:00:00"), LocalDateTime.parse("2020-06-30T00:00:00"), 1, 35455, 3, new BigDecimal("9.99"), "EUR");
//        List<Pricing> pricings = new ArrayList<>();
//        pricings.add(pricing);
//        // Mockeamos el servicio para que devuelva el objeto Pricing que acabamos de crear
//        when(pricingRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(pricings);
//        Mockito.when(pricingService.getValidPrice(date, brandId, productId)).thenReturn(pricing);
//
//        // Realizamos la llamada al endpoint
//        String url = "/pricing/date/2020-06-14-10-00/brand/1/product/35455";
//        mockMvc.perform(MockMvcRequestBuilders.get(url))
//
//                // Verificamos que la respuesta sea 200 OK
//                .andExpect(MockMvcResultMatchers.status().isOk())
//
//                // Verificamos que la respuesta sea el objeto Pricing que esperamos
//                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(pricingMapper.toCustomDto(pricing))));
//    }

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