package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    void delete(int productId);
    List<Product> all();
    List<Product> listProductsAccordingToSearchKeyword(String keyword);

    List<Product> listCheapestProducts();

    Product findById(int productId);
    Product update(Product product, int productId);
}

