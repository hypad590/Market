package com.hypad.Market.repository;

import com.hypad.Market.model.Product;

import java.util.List;

public interface ProductRepository {
   List<Product> getProducts();
   void addProduct(Product product);
}
