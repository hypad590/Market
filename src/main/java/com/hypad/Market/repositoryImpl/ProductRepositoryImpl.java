package com.hypad.Market.repositoryImpl;

import com.hypad.Market.model.Product;
import com.hypad.Market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Qualifier("postgresJdbcTemplate")
    public final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImpl(@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getProducts() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (productName, price) " +
                "VALUES (:productName, :price)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("productName", product.getProductName())
                .addValue("price", product.getPrice());
        jdbcTemplate.update(sql, parameterSource);
    }
}
