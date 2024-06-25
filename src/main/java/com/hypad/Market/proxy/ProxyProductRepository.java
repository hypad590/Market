package com.hypad.Market.proxy;

import com.hypad.Market.model.Product;
import com.hypad.Market.repository.ProductRepository;
import com.hypad.Market.repositoryImpl.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class ProxyProductRepository implements ProductRepository {

    private ProductRepositoryImpl productRepositoryImpl;

    @Qualifier("postgresJdbcTemplate")
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProxyProductRepository(ProductRepositoryImpl productRepositoryImpl,@Qualifier("postgresJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.productRepositoryImpl = productRepositoryImpl;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Product> getProducts() {
        if(productRepositoryImpl == null){
            productRepositoryImpl = new ProductRepositoryImpl(namedParameterJdbcTemplate);
        }
        System.out.println("Proxy: fetching all products");
        return productRepositoryImpl.getProducts();
    }

    @Override
    public void addProduct(Product product) {
        if(productRepositoryImpl == null){
            productRepositoryImpl = new ProductRepositoryImpl(namedParameterJdbcTemplate);
        }
        System.out.println("Proxy: adding product");
        productRepositoryImpl.addProduct(product);
    }
}
