package com.accesa.pricecomparator.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "product_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String productId;
    private String productName;
    private String category;
    private String brand;
    private double packageQuantity;
    private String packageUnit;
    private double price;
    private String currency;
    private String store;
    private LocalDate date;
}
