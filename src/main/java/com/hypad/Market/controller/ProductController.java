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

    //todo the values must be requested instead of product
    // and then new Product().setPrice ...
    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("product") Product product){
        productService.addProduct(product);
        return "redirect:/products";
    }

}
