package com.hypad.Market.proxy;

import com.hypad.Market.Service.OrderService;
import com.hypad.Market.Service.ProductService;
import com.hypad.Market.controller.ProductController;
import com.hypad.Market.model.Product;
import com.hypad.Market.repository.OrderControllerInt;

public class ProxyController implements OrderControllerInt {

    private ProductController productController;

    private final ProductService productService;

    private final OrderService orderService;

    public ProxyController(ProductController productController, ProductService productService, OrderService orderService) {
        this.productController = productController;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void addProductToOrder(Product product) {
        if(productController == null){
            productController = new ProductController(productService,orderService);
        }
        System.out.println("Proxy: adding product to order");
        productController.addProductToOrder(product);
    }
}
