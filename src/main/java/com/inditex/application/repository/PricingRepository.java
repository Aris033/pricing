package com.inditex.application.repository;

import com.inditex.domain.Pricing;

import java.util.List;

public interface PricingRepository {

   Pricing findByPriceList(long priceList);

   List<Pricing> findByBrandIdAndProductId(int brandId, int productId);

   Pricing save(Pricing pricing);

}
