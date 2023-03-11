package com.inditex.application.repository;

import com.inditex.domain.Pricing;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricingRepository {

   /**
    * Retrieves the list of valid pricings for a specific brand and product.
    * @param brandId the ID of the brand
    * @param productId the ID of the product
    * @return a list of valid Pricing objects for the specified brand and product
    */
   List<Pricing> findByBrandIdAndProductId(int brandId, int productId);

   /**
    * Saves the specified pricing information to the repository.
    * @param pricing the Pricing object containing the pricing information to be saved
    * @return the Pricing object representing the saved pricing information
    */
   Pricing save(Pricing pricing);


   /**
    * Retrieves the pricing information for the specified price list.
    * @param priceList the ID of the price list to retrieve pricing information for
    * @return the Pricing object containing the pricing information for the specified price list, or null if no pricing information is found
    */
   Pricing findByPriceList(long priceList);

}
