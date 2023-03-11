package com.inditex.infrastructure.db.springdata.repository;

import com.inditex.application.repository.PricingRepository;
import com.inditex.domain.Pricing;
import com.inditex.infrastructure.db.springdata.mapper.PriceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PricingDboRepository implements PricingRepository {

    private final PriceRepository priceRepository;

    private final PriceEntityMapper priceEntityMapper;

    @Override
    public Pricing findByPriceList(long priceList) {

        return priceEntityMapper.toDomain(priceRepository.findByPriceList(priceList));

    }

    @Override
    public List<Pricing> findByBrandIdAndProductId(int brandId, int productId) {

        return priceEntityMapper.toListDomain(priceRepository.findByBrandIdAndProductId(brandId, productId));

    }

    @Override
    public Pricing save(Pricing pricing) {

        return priceEntityMapper.toDomain(priceRepository.save(priceEntityMapper.toDbo(pricing)));

    }
}
