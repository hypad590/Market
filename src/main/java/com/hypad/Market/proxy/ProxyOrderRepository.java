package com.hypad.Market.proxy;

import com.hypad.Market.model.Order;
import com.hypad.Market.repository.OrderRepository;
import com.hypad.Market.repositoryImpl.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class ProxyOrderRepository implements OrderRepository {
    private OrderRepositoryImpl orderRepositoryImpl;
    @Qualifier("postgresJdbcTemplate")
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProxyOrderRepository(OrderRepositoryImpl orderRepositoryImpl,@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.orderRepositoryImpl = orderRepositoryImpl;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    @Override
    public void createOrder(Order order) {
        if (orderRepositoryImpl == null){
            orderRepositoryImpl = new OrderRepositoryImpl(namedParameterJdbcTemplate);
        }
        System.out.println("Proxy: Creating order with id ");
        orderRepositoryImpl.createOrder(order);
    }

    @Override
    public Order getOrder(String orderId) {
        if (orderRepositoryImpl == null) {
            orderRepositoryImpl = new OrderRepositoryImpl(namedParameterJdbcTemplate);
        }
        System.out.println("Proxy: Fetching order with ID: soon "); //todo (just make every String id as int)
        //todo and debug the creating order
        return orderRepositoryImpl.getOrder(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        if (orderRepositoryImpl == null) {
            orderRepositoryImpl = new OrderRepositoryImpl(namedParameterJdbcTemplate);
        }
        System.out.println("Proxy: Fetching all orders");
        return orderRepositoryImpl.getAllOrders();
    }
}
