package com.rewedigital.examples.workshop.productstock.stock;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {

    private Map<String, Stock> productStock = new HashMap<>();

    public Stock getStock(String productId) {
        return productStock.getOrDefault(productId,
                new Stock.Builder()
                        .withProductId(productId)
                        .withAmount(0)
                        .build()
        );
    }

    public Stock setStock(String productId, int amount) {

        var stock = productStock.computeIfAbsent(productId, id ->
                new Stock.Builder()
                        .withProductId(id)
                        .build()
        );
        stock.setAmount(amount);

        return stock;
    }

}
