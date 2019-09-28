package com.rewedigital.examples.msintegration.productstock.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class StockWebController {

    private final JpaStockRepository stockRepository;

    @Autowired
    public StockWebController(JpaStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping("/stock/{productId}")
    public ModelAndView productStock(@PathVariable("productId") final String productId,
                                     HttpServletResponse response) {

        final ModelAndView result = new ModelAndView("stock");

        Stock stock = stockRepository
                .findById(productId)
                .orElse(new Stock.Builder()
                        .withProductId(productId)
                        .withAmount(0)
                        .build());

        result.addObject("stock", stock);

        return result;
    }

}
