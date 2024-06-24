package com.hypad.Market.controller;

import com.hypad.Market.Service.ProductService;
import com.hypad.Market.model.Product;
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
public class ProductController {

    public final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
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

        productService.addProduct(product); //todo addToCart method that stores products in userData
        return "redirect:/products";
    }

}
