package com.hypad.Market.controller;

import com.hypad.Market.Service.OrderService;
import com.hypad.Market.Service.ProductService;
import com.hypad.Market.model.Order;
import com.hypad.Market.model.Product;
import com.hypad.Market.repository.OrderControllerInt;
import com.hypad.Market.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final UserRepository userRepository;

    @Autowired
    public ProductController(ProductService productService, OrderService orderService,UserRepository userRepository) {
        this.productService = productService;
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String products(
            Model model) {
        List<Product> productList = productService.getProducts();
        model.addAttribute("products", productList);
        return "products";
    }


    @PostMapping("/addToCart")
    public String addToCart(
            @RequestParam("productName") String productName,
            @RequestParam("price") String price,
            HttpServletRequest request){

        String name = "";

        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        if(cookies != null){
            if(session != null) {
                if(session.getAttribute("user") != null){
                    for (Cookie cookie : cookies) {
                        if ("token".equals(cookie.getName())) {
                            String token = cookie.getValue();
                            name = userRepository.getUserNameByToken(token);
                            System.out.println("Name: " + name + " Token: " + token);
                        }
                    }
                }
                else{
                    throw new RuntimeException("attr user is null"); //todo (ATTR USER IS NULL NOW)
                }
            }
        }

        Product product = Product.builder()
                .productName(productName)
                .price(price)
                .build();

        if(!name.isEmpty()){
            addProductToOrder(product, name);
        }
        else{
            throw new RuntimeException("Name is empty");
        }
        return "redirect:/products";
    }

    @Override
    public void addProductToOrder(Product product, String name) {
        Order order = Order
                .builder()
                .productName(product.getProductName())
                .totalPrice(product.getPrice())
                .name(name)
                .build(); //no card data rn

        orderService.createOrder(order);
    }

    //todo make the field totalPrice in Order as price and in orderTable same thing,
    // add card data fields in orderTable
}
