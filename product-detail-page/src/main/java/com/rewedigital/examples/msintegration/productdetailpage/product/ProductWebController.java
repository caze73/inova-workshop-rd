package com.rewedigital.examples.msintegration.productdetailpage.product;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductWebController {

    private final JpaProductRepository products;
    private static final Map<String, Product> defaultProducts = defaultProducts();
    private String headerFooterSrc;
    private String stockSrc;

    // example data
    private static Map<String, Product> defaultProducts() {
        final Map<String, Product> result = new HashMap<>();
        result.put("2670536", new Product.Builder().withName("Original Wagner Big Pizza Supreme 420g")
            .withVendor("Original Wagner").withPrice("€ 2,99").withProductNumber("2670536")
            .withDescription(
                "Big Taste, Big Fun and Big Pizza! Erlebe unsere großartige Kombination aus außergewöhnlichen Topping & Boden mit dem Big Crunch.\r\n"
                    + "\r\n"
                    + "Entdecke die Wagner Big Pizza Supreme: luftig-locker und herzhaft-saftig, belegt mit feinstem Mozzarella, "
                    + "leckeren Champignons, naturgereifter Edel-Salami, echtem gekochten Schinken und sonnengereiften Tomaten. Unser Geheimrezept für "
                    + "diesen Supreme-Genuss lautet: guter Teig, viel Belag und unser traditionelles Original Wagner Backverfahren. So entsteht die "
                    + "großartige Kombination aus herzhaft-saftigem Belag und dem luftig-lockeren Boden mit dem einzigartigen Big Crunch. Diese Big Pizza "
                    + "von Wagner macht ihrem Namen alle Ehre; eine Wagner Pizza, die Du im Haus haben solltest, wenn der große Hunger kommt oder ein paar "
                    + "gute Freunde! " + "- mit extra viel Käse " + "- extra reichhaltig belegt "
                    + "- großartige Kombination aus naturgereifter Salami, saftigem Kochschinken und leckeren Champignons\r\n"
                    + "- luftig-lockerer Teig mit dem Big Crunch")
            .withImage("25165635").build());
        return result;
    };

    @Autowired
    public ProductWebController(final JpaProductRepository products,
        final @Value("${header-footer-src}") String headerFooterSrc,
        final @Value("${stock-src}") String stockSrc) {
        this.products = products;
        this.headerFooterSrc = headerFooterSrc;
        this.stockSrc = stockSrc;
    }

    @GetMapping("/products/{productId}")
    public ModelAndView productDetails(@PathVariable("productId") final String productId,
        HttpServletResponse response) {
        return getProductDetails(productId);
    }

    @GetMapping("/products")
    public ModelAndView productList(HttpServletResponse response) {
        return getProductList();
    }

    private ModelAndView getProductList() {
        ModelAndView result = new ModelAndView("productList");
        ArrayList<Product> productList = new ArrayList<>(products.findAll());
        productList.addAll(defaultProducts.values());
        result.addObject("products", productList);
        return result;
    }

    private ModelAndView getProductDetails(final String productId) {
        return find(productId).map(p -> {
            final ModelAndView result = new ModelAndView("product");
            result.addObject("product", p);
            result.addObject("headerFooterSrc", headerFooterSrc);
            result.addObject("stockSrc", stockSrc);
            return result;
        }).orElseThrow(() -> productNotFound(productId));
    }

    private Optional<Product> find(final String productId) {
        Optional<Product> productFromRepo = products.findByProductNumber(productId);
        if (productFromRepo.isPresent()) {
            return productFromRepo;
        }
        return Optional.ofNullable(defaultProducts.get(productId));

    }

    private RuntimeException productNotFound(final String id) {
        throw new RuntimeException(format("product [%s] not found", id));
    }

}
