package com.hypad.Market.controller;

import com.hypad.Market.Service.OrderService;
import com.hypad.Market.model.Order;
import com.hypad.Market.model.Product;
import com.hypad.Market.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public String cart(@ModelAttribute("name") String name,
                       @ModelAttribute("email") String email,
                       @ModelAttribute("password") String password,
                       Model model) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        model.addAttribute("user", user);

        return "cart";
    }

    @PostMapping("/createOrder")
    public String createOrder(@RequestParam("name") String name,
                              @RequestParam("productName") String productName,
                              @RequestParam("totalPrice") String totalPrice,
                              @RequestParam("ccVV") String ccVV,
                              @RequestParam("ccNumber") String ccNumber,
                              @RequestParam("ccExpiration") String ccExpiration)
    {
        Order order = new Order();
        order.setName(name);
        order.setProductName(productName);
        order.setTotalPrice(totalPrice);
        order.setCcNumber(ccNumber);
        order.setCcExpiration(ccExpiration);
        order.setCcVV(ccVV);
        orderService.createOrder(order);

        return "redirect:/order/list";
    }

    @PostMapping("/orderById/{id}")
    public String orderById(@PathVariable String id, Model model){
        Order order = orderService.getOrder(id);
        model.addAttribute("order",order);
        return "orderById";
    }

    @GetMapping("/list")
    public String orders(Model model){
       model.addAttribute("orders",orderService.getAllOrders());
       return "list";
    }
}
