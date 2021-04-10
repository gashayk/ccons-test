package com.inditex.cconsulting.app.repository;

import com.inditex.cconsulting.app.model.entities.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricingRepository extends JpaRepository<ProductPrice, Long> {

    List<ProductPrice> findAllByStartDateGreaterThanEqualAndBrandIdAndProductId(LocalDateTime startDate, Long brandId, Long productId);
}
