package com.accesa.pricecomparator.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accesa.pricecomparator.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, UUID>{

	Optional<Discount> findByProductIdAndStoreAndFromDateLessThanEqualAndToDateGreaterThanEqual(
		    String productId, String store, LocalDate from, LocalDate to);
	
	@Query("SELECT d FROM Discount d WHERE :targetDate BETWEEN d.fromDate AND d.toDate")
	List<Discount> findActiveDiscounts(@Param("targetDate") LocalDate date);
	
	@Query("SELECT d FROM Discount d WHERE d.fromDate >= :since")
	List<Discount> findDiscountsStartingAfter(@Param("since") LocalDate since);

	List<Discount> findByFromDate(LocalDate fromDate);

}
