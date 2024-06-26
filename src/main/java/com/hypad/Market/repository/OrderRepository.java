package com.hypad.Market.repository;

import com.hypad.Market.model.Order;
import com.hypad.Market.model.Product;

import java.util.List;

public interface OrderRepository {
    void createOrder(Order order);
    Order getOrder(String orderId);
    List<Order> getAllOrders();
}
