package com.inditex.infrastructure.rest.spring.controllers;


import com.inditex.application.repository.PricingRepository;
import com.inditex.application.service.PricingService;
import com.inditex.domain.Pricing;
import com.inditex.exception.ErrorResponse;
import com.inditex.exception.NotFoundException;
import com.inditex.infrastructure.config.spring.TestRestTemplateConfig;
import com.inditex.infrastructure.rest.spring.dto.CustomDto;
import com.inditex.infrastructure.rest.spring.mapper.PricingMapper;
import com.inditex.utils.UtilsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.inditex.infrastructure.config.spring.SpringBootServiceTest.beforeAll;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Resources.class, TestRestTemplateConfig.class})
@AutoConfigureTestDatabase
class ResourcesTest {

    @InjectMocks
    private Resources resources;

    @Autowired
    private RestTemplate restTemplate;

    @MockBean
    private PricingService pricingService;

    @MockBean
    private PricingMapper pricingMapper;

    @MockBean
    private PricingRepository pricingRepository;

    @Value("${test.baseUri}")
    private String baseUri;


    @BeforeEach
    void setup() throws InterruptedException {
        MockitoAnnotations.initMocks(this);
        resources = new Resources(pricingService, pricingMapper);
        beforeAll();
    }

    @Test
    public void testGetValidPrice() throws Exception {
        int brandId = 1;
        int productId = 35455;
        Pricing pricing = UtilsTest.createPricing();
        pricingRepository.save(pricing);
        LocalDateTime date = pricing.getStartDate().plusDays(2);
        when(pricingService.getValidPrice(pricing.getStartDate(), brandId, productId)).thenReturn(pricing);
        when(pricingMapper.toCustomDto(pricing)).thenReturn(new CustomDto());
        String uri = buildUri(date, brandId, productId);
        ResponseEntity<CustomDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, CustomDto.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getPrice()).isEqualTo("38.95");
    }

    // -- TEST DE PRUEBAS:
    @Test
    public void testGetValidPrice_request1() throws Exception {
        int brandId = 1;
        int productId = 35455;
        Pricing pricing = UtilsTest.createPricing();
        pricingRepository.save(pricing);
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        when(pricingService.getValidPrice(date, brandId, productId)).thenReturn(pricing);
        when(pricingMapper.toCustomDto(pricing)).thenReturn(new CustomDto());
        String uri = buildUri(date, brandId, productId);
        ResponseEntity<CustomDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, CustomDto.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getPrice()).isEqualTo("35.50");
    }

    @Test
    public void testGetValidPrice_request2() throws Exception {
        int brandId = 1;
        int productId = 35455;
        Pricing pricing = UtilsTest.createPricing();
        pricingRepository.save(pricing);
        LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");
        when(pricingService.getValidPrice(date, brandId, productId)).thenReturn(pricing);
        when(pricingMapper.toCustomDto(pricing)).thenReturn(new CustomDto());
        String uri = buildUri(date, brandId, productId);
        ResponseEntity<CustomDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, CustomDto.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getPrice()).isEqualTo("25.45");
    }

    @Test
    public void testGetValidPrice_request3() throws Exception {
        int brandId = 1;
        int productId = 35455;
        Pricing pricing = UtilsTest.createPricing();
        pricingRepository.save(pricing);
        LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00");
        when(pricingService.getValidPrice(date, brandId, productId)).thenReturn(pricing);
        when(pricingMapper.toCustomDto(pricing)).thenReturn(new CustomDto());
        String uri = buildUri(date, brandId, productId);
        ResponseEntity<CustomDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, CustomDto.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getPrice()).isEqualTo("35.50");
    }
    @Test
    public void testGetValidPrice_request4() throws Exception { int brandId = 1;
        int productId = 35455;
        Pricing pricing = UtilsTest.createPricing();
        pricingRepository.save(pricing);
        LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00");
        when(pricingService.getValidPrice(date, brandId, productId)).thenReturn(pricing);
        when(pricingMapper.toCustomDto(pricing)).thenReturn(new CustomDto());
        String uri = buildUri(date, brandId, productId);
        ResponseEntity<CustomDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, CustomDto.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getPrice()).isEqualTo("30.50");
    }

    @Test
    public void testGetValidPrice_request5() throws Exception {
        int brandId = 1;
        int productId = 35455;
        Pricing pricing = UtilsTest.createPricing();
        pricingRepository.save(pricing);
        LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");
        when(pricingService.getValidPrice(date, brandId, productId)).thenReturn(pricing);
        when(pricingMapper.toCustomDto(pricing)).thenReturn(new CustomDto());
        String uri = buildUri(date, brandId, productId);
        ResponseEntity<CustomDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, CustomDto.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getPrice()).isEqualTo("38.95");
    }
//    @Test
//    public void testGetValidPrice_priceNotFound() throws Exception {
//        int brandId = 1;
//        int productId = 35455;
//        LocalDateTime date = LocalDateTime.parse("2029-06-14T10:00:00");
//        when(pricingService.getValidPrice(date, brandId, productId)).thenThrow(new NotFoundException("Price not found"));
//        String uri = buildUri(date, brandId, productId);
//        ResponseEntity<CustomDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, CustomDto.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    public void testGetValidPrice_internalServerError() throws Exception {
//        int brandId = 1;
//        int productId = 35455;
//        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
//        when(pricingService.getValidPrice(date, brandId, productId)).thenThrow(new RuntimeException("Internal server error"));
//        String uri = buildUri(date, brandId, productId);
//        ResponseEntity<ErrorResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, ErrorResponse.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    private String buildUri(LocalDateTime date, int brandId, int productId) {
        return UriComponentsBuilder.fromHttpUrl(baseUri)
                .path("/pricing/date/{date}/brand/{brandId}/product/{productId}")
                .buildAndExpand(
                        formatDate(date),
                        brandId,
                        productId)
                .toUriString();
    }

    private String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
    }

}
