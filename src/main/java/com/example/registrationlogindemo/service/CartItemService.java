package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.CartItem;
import com.example.registrationlogindemo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    CartItem save(CartItem item);
    void delete(int itemId);
    List<CartItem> all();
    CartItem findById(int itemId);
    CartItem update(CartItem item);

    List<Product> topThreeMostOrderedProduct();
}

