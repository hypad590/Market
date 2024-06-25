package com.hypad.Market.controller;

import com.hypad.Market.Service.OrderService;
import com.hypad.Market.Service.ProductService;
import com.hypad.Market.model.Order;
import com.hypad.Market.model.Product;
import com.hypad.Market.repository.OrderControllerInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController implements OrderControllerInt {

    public final ProductService productService;
    public final OrderService orderService;

    @Autowired
    public ProductController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping
    public String products(
            Model model) {
        List<Product> productList = productService.getProducts();
        model.addAttribute("products", productList);
        return "products";
    }


    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("productName") String name,
                            @RequestParam("price") String price){

        Product product = Product.builder()
                .productName(name)
                .price(price)
                .build();

        addProductToOrder(product);
        return "redirect:/products";
    }

    @Override
    public void addProductToOrder(Product product) {
        Order order = Order
                .builder()
                .productName(product.getProductName())
                .totalPrice(product.getPrice())
                .name("null")
                .build(); //no card data rn

        orderService.createOrder(order);
    }

    //todo make the field totalPrice in Order as price and in orderTable same thing, *LATER
    // add card data fields in orderTable *THINKING_BOUT_THAT
    //todo also add the service for workers to manage orders
}
