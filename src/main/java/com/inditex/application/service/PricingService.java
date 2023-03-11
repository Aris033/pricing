package com.inditex.application.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import com.inditex.exception.NotFoundException;
import com.inditex.application.repository.PricingRepository;
import org.springframework.stereotype.Service;

import com.inditex.domain.Pricing;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final PricingRepository pricingRepository;

    public Pricing getValidPrice(LocalDateTime date, int brandId, int productId) throws NotFoundException {
        List<Pricing> byPriceList = pricingRepository.findByBrandIdAndProductId(brandId, productId);

        return byPriceList.stream()
                .filter(pricing -> validateDate(pricing, date))
                .max(Comparator.comparingInt(Pricing::getPriority))
                .orElseThrow(() -> new NotFoundException("Price not valid for date: " + date));
    }

    private boolean validateDate(Pricing pricing, LocalDateTime date) {
        LocalDateTime startDate = pricing.getStartDate();
        LocalDateTime endDate = pricing.getEndDate();
        return date.isEqual(startDate) || date.isEqual(endDate) || (date.isAfter(startDate) && date.isBefore(endDate));
    }

}
