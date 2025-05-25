package com.accesa.pricecomparator.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accesa.pricecomparator.entity.ProductPrice;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, UUID>{
	
	List<ProductPrice> findByProductIdAndDate(String productId, LocalDate date);
	
	Optional<ProductPrice> findFirstByProductIdAndStoreAndDate(String productId, String store, LocalDate date);
	
	@Query("""
		    SELECT p FROM ProductPrice p
		    WHERE p.productId = :productId
		      AND (:store IS NULL OR p.store = :store)
		      AND (:brand IS NULL OR p.brand = :brand)
		      AND (:category IS NULL OR p.category = :category)
		    ORDER BY p.date ASC
		""")
		List<ProductPrice> findPriceHistory(
		    @Param("productId") String productId,
		    @Param("store") String store,
		    @Param("brand") String brand,
		    @Param("category") String category
		);
	
	@Query("""
		    SELECT p FROM ProductPrice p
		    WHERE p.category = :category
		      AND p.productId <> :productId
		      AND p.date = :date
		""")
		List<ProductPrice> findSubstitutes(String category, String productId, LocalDate date);

	Optional<ProductPrice> findFirstByProductIdAndDate(String productId, LocalDate date);

}
