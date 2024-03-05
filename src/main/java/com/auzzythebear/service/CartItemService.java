package com.auzzythebear.service;

import com.auzzythebear.entity.CartItem;
import com.auzzythebear.entity.Product;
import java.util.List;

public interface CartItemService {
  CartItem save(CartItem item);

  void delete(int itemId);

  List<CartItem> all();

  CartItem findById(int itemId);

  CartItem update(CartItem item);

  List<Product> topThreeMostOrderedProduct();
}
