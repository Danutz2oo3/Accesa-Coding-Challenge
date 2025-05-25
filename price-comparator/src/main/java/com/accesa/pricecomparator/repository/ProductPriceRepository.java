package com.accesa.pricecomparator.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accesa.pricecomparator.entity.ProductPrice;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, UUID>{

}
