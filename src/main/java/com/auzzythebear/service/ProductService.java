package com.auzzythebear.service;

import com.auzzythebear.entity.Product;
import java.util.List;

public interface ProductService {
  Product save(Product product);

  void delete(int productId);

  List<Product> all();

  List<Product> listProductsAccordingToSearchKeyword(String keyword);

  List<Product> listCheapestProducts();

  Product findById(int productId);

  Product update(Product product, int productId);
}
