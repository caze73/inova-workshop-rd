package com.rewedigital.examples.workshop.productstock.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class StockWebController {

    @GetMapping("/stock/{productId}")
    public String productStock(@PathVariable("productId") final String productId,
                                     HttpServletResponse response) {

        return "stock";
    }

}
