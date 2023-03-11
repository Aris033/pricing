package com.inditex.infrastructure.rest.spring.controllers;

import com.inditex.application.service.PricingService;
import com.inditex.domain.Pricing;
import com.inditex.exception.ErrorResponse;
import com.inditex.exception.NotFoundException;
import com.inditex.infrastructure.rest.spring.mapper.PricingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class Resources {

    private final PricingService pricingService;
    private final PricingMapper pricingMapper;

    @GetMapping("pricing/date/{date}/brand/{brandId}/product/{productId}")
    public ResponseEntity<?> getValidPrice(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm") LocalDateTime date,
                                           @PathVariable int brandId, @PathVariable int productId) {
        try {
            Pricing pricing = pricingService.getValidPrice(date, brandId, productId);
            return ResponseEntity.ok(pricingMapper.toCustomDto(pricing));
        } catch (NotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse("Price not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
