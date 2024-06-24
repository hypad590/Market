package com.hypad.Market.Service;

import com.hypad.Market.model.Order;
import com.hypad.Market.model.Product;
import com.hypad.Market.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(Order order) {
        orderRepository.createOrder(order);
    }

    public Order getOrder(String orderId) {
        return orderRepository.getOrder(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }
}
