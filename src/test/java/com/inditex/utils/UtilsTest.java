package com.inditex.utils;

import com.inditex.domain.Pricing;
import com.inditex.infrastructure.db.springdata.dbo.BrandEntity;
import com.inditex.infrastructure.db.springdata.dbo.PriceId;
import com.inditex.infrastructure.db.springdata.dbo.PricesEntity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

    private static final LocalDateTime START = LocalDateTime.parse("2020-06-14T10:00:00");
    private static final LocalDateTime END = LocalDateTime.parse("2020-07-14T10:00:00");

    public static Pricing createPricing() {
        Pricing pricing = new Pricing();
        pricing.setBrandId(1);
        pricing.setStartDate(START);
        pricing.setEndDate(END);
        pricing.setPriceList(12345L);
        pricing.setProductId(35455);
        pricing.setPriority(1);
        pricing.setPrice(new BigDecimal("99.99"));
        pricing.setCurr("USD");

        return pricing;
    }

    public static List<PricesEntity> createPricesEntities() {
        BrandEntity brand = new BrandEntity();
        brand.setBrandId(1);
        brand.setName("Brand 1");

        PricesEntity pricesEntity1 = new PricesEntity();
        pricesEntity1.setBrand(brand);
        pricesEntity1.setBrandId(1);
        pricesEntity1.setStartDate(START);
        pricesEntity1.setEndDate(END);
        pricesEntity1.setPriceList(1);
        pricesEntity1.setProductId(1);
        pricesEntity1.setPriority(0);
        pricesEntity1.setPrice(new BigDecimal("99.99"));
        pricesEntity1.setCurr("USD");

        PricesEntity pricesEntity2 = new PricesEntity();
        pricesEntity2.setBrand(brand);
        pricesEntity2.setBrandId(1);
        pricesEntity2.setStartDate(START.plusDays(2));
        pricesEntity2.setEndDate(END.plusMonths(6));
        pricesEntity2.setPriceList(2);
        pricesEntity2.setProductId(1);
        pricesEntity2.setPriority(1);
        pricesEntity2.setPrice(new BigDecimal("89.99"));
        pricesEntity2.setCurr("USD");

        List<PricesEntity> pricesEntities = new ArrayList<>();
        pricesEntities.add(pricesEntity1);
        pricesEntities.add(pricesEntity2);

        return pricesEntities;
    }

    public static PricesEntity createPricesEntity() {
        BrandEntity brand = new BrandEntity();
        brand.setBrandId(1);
        brand.setName("Brand 1");

        PricesEntity pricesEntity = new PricesEntity();
        pricesEntity.setBrand(brand);
        pricesEntity.setBrandId(1);
        pricesEntity.setStartDate(START);
        pricesEntity.setEndDate(END);
        pricesEntity.setPriceList(12345L);
        pricesEntity.setProductId(2);
        pricesEntity.setPriority(1);
        pricesEntity.setPrice(new BigDecimal("99.99"));
        pricesEntity.setCurr("USD");

        return pricesEntity;
    }

    public static PriceId createPriceId() {
        PriceId priceId = new PriceId();
        priceId.setBrandId(1);
        priceId.setStartDate(START);
        priceId.setEndDate(END);
        priceId.setPriceList(12345L);
        priceId.setProductId(2);

        return priceId;
    }

    // Crea una lista de objetos Pricing con una cantidad dada de elementos
    public static List<Pricing> createPricingList(int quantity) {
        List<Pricing> pricingList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Pricing pricing = createPricing();
            pricingList.add(pricing);
        }
        return pricingList;
    }

    // Obtiene una lista de precios válidos según la fecha y los datos del producto
    public static List<Pricing> getValidPrices(List<Pricing> pricingList, LocalDateTime date, int productId) {
        List<Pricing> validPrices = new ArrayList<>();
        for (Pricing pricing : pricingList) {
            if (pricing.getStartDate().isBefore(date) && pricing.getEndDate().isAfter(date) && pricing.getProductId() == productId) {
                validPrices.add(pricing);
            }
        }
        return validPrices;
    }

    // Obtiene el precio más bajo de una lista de precios
    public static BigDecimal getLowestPrice(List<Pricing> pricingList) {
        BigDecimal lowestPrice = pricingList.get(0).getPrice();
        for (Pricing pricing : pricingList) {
            if (pricing.getPrice().compareTo(lowestPrice) < 0) {
                lowestPrice = pricing.getPrice();
            }
        }
        return lowestPrice;
    }
}