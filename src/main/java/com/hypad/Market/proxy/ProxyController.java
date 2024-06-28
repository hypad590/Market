package com.hypad.Market.proxy;

import com.hypad.Market.Service.OrderService;
import com.hypad.Market.Service.ProductService;
import com.hypad.Market.controller.ProductController;
import com.hypad.Market.model.Product;
import com.hypad.Market.repository.OrderControllerInt;
import com.hypad.Market.repository.UserRepository;

public class ProxyController implements OrderControllerInt {

    private ProductController productController;

    private final ProductService productService;

    private final OrderService orderService;

    private final UserRepository userRepository;

    public ProxyController(ProductController productController, ProductService productService, OrderService orderService, UserRepository userRepository) {
        this.productController = productController;
        this.productService = productService;
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @Override
    public void addProductToOrder(Product product, String name) {
        if(productController == null){
            productController = new ProductController(productService,orderService,userRepository);
        }
        System.out.println("Proxy: adding product to order");
        productController.addProductToOrder(product, name);
    }
}
