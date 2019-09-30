package com.rewedigital.examples.workshop.productstock.stock;

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

    private final StockService stockService;

    @Autowired
    public StockRestController(StockService stockService) {
        this.stockService = stockService;
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<Stock> updateStock(@PathVariable String productId, @RequestBody(required = false) StockAmount stockAmount) {

        if (StringUtils.isEmpty(productId)) {
            return ResponseEntity.badRequest().build();
        }

        var amount = stockAmount != null ? stockAmount.getAmount() : 0;
        var stock = stockService.setStock(productId, amount);

        return ResponseEntity.ok(stock);
    }
}
