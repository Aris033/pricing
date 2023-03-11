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

    /**
     * Retrieves the latest valid price for a specific brand and product on a specific date.
     * @param date the date for which to retrieve the valid price
     * @param brandId the ID of the brand
     * @param productId the ID of the product
     * @return the latest valid price for the specified brand, product, and date
     * @throws NotFoundException if no valid price is found for the specified brand, product, and date
     */
    public Pricing getValidPrice(LocalDateTime date, int brandId, int productId) throws NotFoundException {
        List<Pricing> byPriceList = pricingRepository.findByBrandIdAndProductId(brandId, productId);

        // Filter the valid prices for the given date
        return byPriceList.stream()
                .filter(pricing -> validateDate(pricing, date))
                // Sort the valid prices by priority (the highest priority is the latest price)
                .max(Comparator.comparingInt(Pricing::getPriority))
                // If no valid price is found, throw a NotFoundException
                .orElseThrow(() -> new NotFoundException("Price not valid for date: " + date));
    }

    /**
     * Validates whether the given date falls within the valid date range for the given price.
     * @param pricing the price to validate
     * @param date the date to validate
     * @return true if the given date falls within the valid date range for the given price, false otherwise
     */
    private boolean validateDate(Pricing pricing, LocalDateTime date) {
        LocalDateTime startDate = pricing.getStartDate();
        LocalDateTime endDate = pricing.getEndDate();
        return date.isEqual(startDate) || date.isEqual(endDate) || (date.isAfter(startDate) && date.isBefore(endDate));
    }

}
