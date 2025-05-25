package com.accesa.pricecomparator.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accesa.pricecomparator.entity.ProductPrice;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, UUID>{
	
	List<ProductPrice> findByProductIdAndDate(String productId, LocalDate date);
	
	Optional<ProductPrice> findFirstByProductIdAndStoreAndDate(String productId, String store, LocalDate date);
	
}
