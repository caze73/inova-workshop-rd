package com.rewedigital.examples.msintegration.productstock.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StockRestController {

    private final JpaStockRepository stockRepository;

    @Autowired
    public StockRestController(JpaStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<Stock> updateStock(@PathVariable String productId, @RequestBody(required = false) StockAmount stockAmount) {

        if (StringUtils.isEmpty(productId)) {
            return ResponseEntity.badRequest().build();
        }

        int amount = stockAmount != null ? stockAmount.getAmount() : 0;

        Stock stock = stockRepository
                .findById(productId)
                .orElse(new Stock.Builder().withProductId(productId).build());
        stock.setAmount(amount);
        stockRepository.save(stock);

        return ResponseEntity.ok(stock);
    }
}
