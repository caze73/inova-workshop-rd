package com.rewedigital.examples.workshop.productstock.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final JpaStockRepository stockRepository;

    @Autowired
    public StockService(JpaStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock getStock(String productId) {

        return stockRepository.findById(productId).orElse(
                new Stock.Builder()
                        .withProductId(productId)
                        .withAmount(0)
                        .build()
        );
    }

    public Stock setStock(String productId, int amount) {

        var stock = getStock(productId);

        stock.setAmount(amount);
        stockRepository.save(stock);

        return stock;
    }

}
