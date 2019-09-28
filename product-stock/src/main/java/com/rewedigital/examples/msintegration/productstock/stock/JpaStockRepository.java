package com.rewedigital.examples.msintegration.productstock.stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStockRepository extends JpaRepository<Stock, String> {
}
