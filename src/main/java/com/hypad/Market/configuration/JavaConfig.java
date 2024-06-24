package com.hypad.Market.configuration;

import com.hypad.Market.proxy.ProxyOrderRepository;
import com.hypad.Market.proxy.ProxyProductRepository;
import com.hypad.Market.proxy.ProxyUserRepository;
import com.hypad.Market.repository.OrderRepository;
import com.hypad.Market.repository.ProductRepository;
import com.hypad.Market.repository.UserRepository;
import com.hypad.Market.repositoryImpl.OrderRepositoryImpl;
import com.hypad.Market.repositoryImpl.ProductRepositoryImpl;
import com.hypad.Market.repositoryImpl.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JavaConfig {
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public OrderRepositoryImpl orderRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new OrderRepositoryImpl(namedParameterJdbcTemplate);
    }

    @Bean
    public OrderRepository orderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, OrderRepositoryImpl orderRepositoryImpl){
        return new ProxyOrderRepository(orderRepositoryImpl,namedParameterJdbcTemplate);
    }

    @Bean
    ProductRepositoryImpl productRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new ProductRepositoryImpl(namedParameterJdbcTemplate);
    }

    @Bean
    public ProductRepository productRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ProductRepositoryImpl productRepositoryImpl){
        return new ProxyProductRepository(productRepositoryImpl,namedParameterJdbcTemplate);
    }

    @Bean
    public UserRepositoryImpl userRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new UserRepositoryImpl(namedParameterJdbcTemplate);
    }

    @Bean
    public UserRepository userRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, UserRepositoryImpl userRepositoryImpl){
        return new ProxyUserRepository(userRepositoryImpl,namedParameterJdbcTemplate);
    }
}
