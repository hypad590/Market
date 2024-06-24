package com.hypad.Market.configuration;

import com.hypad.Market.proxy.ProxyOrderRepository;
import com.hypad.Market.repository.OrderRepository;
import com.hypad.Market.repositoryImpl.OrderRepositoryImpl;
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
}
