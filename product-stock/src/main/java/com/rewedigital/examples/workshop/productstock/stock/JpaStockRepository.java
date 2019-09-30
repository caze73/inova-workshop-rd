package com.rewedigital.examples.workshop.productstock.stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStockRepository extends JpaRepository<Stock, String> {
}
