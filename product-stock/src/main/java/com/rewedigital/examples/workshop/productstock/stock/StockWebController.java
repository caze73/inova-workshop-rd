package com.rewedigital.examples.workshop.productstock.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class StockWebController {

    private final StockService stockService;

    @Autowired
    public StockWebController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stock/{productId}")
    public ModelAndView productStock(@PathVariable("productId") final String productId,
                                     HttpServletResponse response) {

        var result = new ModelAndView("stock");
        var stock = stockService.getStock(productId);

        result.addObject("stock", stock);

        return result;
    }

}
