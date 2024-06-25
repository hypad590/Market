package com.hypad.Market.repositoryImpl;

import com.hypad.Market.model.Order;
import com.hypad.Market.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Qualifier("postgresJdbcTemplate")
    public final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepositoryImpl(@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createOrder(Order order) {
        String sql = "INSERT INTO ordertable (name, products, totalPrice) " +
                "VALUES (:name, :products, :totalPrice) ";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", order.getName())
                .addValue("products", order.getProductName())
                .addValue("totalPrice", order.getTotalPrice());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public Order getOrder(String orderId) {
        String sql = "SELECT * FROM ordertable WHERE id = :orderId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("orderId", orderId);
        return jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Order.class));
    }

    @Override
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM ordertable";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
    }
}
