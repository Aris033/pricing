package com.inditex.infrastructure.db.springdata.repository;

import com.inditex.infrastructure.db.springdata.dbo.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PricesEntity, Long> {

    PricesEntity findByPriceList(long priceList);

    List<PricesEntity> findByBrandIdAndProductId(int priceList, int productId);

}
