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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JavaConfig {

    @Primary
    @Bean(name="postgresDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/postgres")
                .username("postgres")
                .password("hypadJ")
                .build();
    }

    @Bean(name="jwt_securityDataSource")
    @ConfigurationProperties(prefix = "spring.jwtdatasource")
    public DataSource jwtSecurityDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/jwt_security")
                .username("postgres")
                .password("hypadJ")
                .build();
    }

    @Primary
    @Bean(name = "postgresJdbcTemplate")
    public NamedParameterJdbcTemplate postgresJdbcTemplate(@Qualifier("postgresDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = "jwtJdbcTemplate")
    public NamedParameterJdbcTemplate jwtJdbcTemplate(@Qualifier("jwt_securityDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Primary
    @Bean(name = "postgresTransactionManager")
    public DataSourceTransactionManager postgresTransactionManager(@Qualifier("postgresDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "jwtTransactionManager")
    public DataSourceTransactionManager jwtTransactionManager(@Qualifier("jwt_securityDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public OrderRepositoryImpl orderRepositoryImpl(@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new OrderRepositoryImpl(namedParameterJdbcTemplate);
    }

    @Bean
    public OrderRepository orderRepository(@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate, OrderRepositoryImpl orderRepositoryImpl){
        return new ProxyOrderRepository(orderRepositoryImpl,namedParameterJdbcTemplate);
    }

    @Bean
    ProductRepositoryImpl productRepositoryImpl(@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new ProductRepositoryImpl(namedParameterJdbcTemplate);
    }

    @Bean
    public ProductRepository productRepository(@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate, ProductRepositoryImpl productRepositoryImpl){
        return new ProxyProductRepository(productRepositoryImpl,namedParameterJdbcTemplate);
    }

    @Bean
    public UserRepositoryImpl userRepositoryImpl(@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new UserRepositoryImpl(namedParameterJdbcTemplate);
    }

    @Primary
    @Bean
    public UserRepository userRepository(@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate, UserRepositoryImpl userRepositoryImpl){
        return new ProxyUserRepository(userRepositoryImpl,namedParameterJdbcTemplate);
    }

}
