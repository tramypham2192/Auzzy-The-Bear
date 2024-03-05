package com.auzzythebear.service.impl;

import com.auzzythebear.entity.Product;
import com.auzzythebear.exceptions.ProductNotExistException;
import com.auzzythebear.repository.ProductRepository;
import com.auzzythebear.service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceMySQL implements ProductService {
  private final ProductRepository productRepository;

  public ProductServiceMySQL(@Autowired ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product save(Product product) {
    Product newProduct = new Product();
    newProduct.setName(product.getName());
    newProduct.setPrice(product.getPrice());
    newProduct.setDescription(product.getDescription());
    newProduct.setImageURL(product.getImageURL());
    productRepository.save(newProduct);
    return newProduct;
  }

  @Override
  public void delete(int productId) {
    Optional<Product> productToDeleteOptional = this.productRepository.findById(productId);
    if (!productToDeleteOptional.isPresent()) {
      System.out.println("This product does not exist");
      ;
    }
    Product productToDelete = productToDeleteOptional.get();
    this.productRepository.delete(productToDelete);
  }

  @Override
  public List<Product> all() {
    return (List<Product>) this.productRepository.findAll();
  }

  @Override
  public List<Product> listProductsAccordingToSearchKeyword(String keyword) {
    if (keyword != null) {
      return productRepository.search(keyword);
    }
    return (List<Product>) this.productRepository.findAll();
  }

  @Override
  public List<Product> listCheapestProducts() {
    return productRepository.sortProductsFromCheapestToMostExpensive();
  }

  @Override
  public Product findById(int productId) {
    Optional<Product> productToFindOptional = this.productRepository.findById(productId);
    if (!productToFindOptional.isPresent()) {
      throw new ProductNotExistException("Product id is invalid " + productId);
    }
    Product productToFind = productToFindOptional.get();
    return productToFind;
  }

  @Override
  public Product update(Product product, int productId) {
    Optional<Product> productToUpdateOptional = this.productRepository.findById(productId);
    if (!productToUpdateOptional.isPresent()) {
      return null;
    }
    Product productToUpdate = productToUpdateOptional.get();
    productToUpdate.setName(product.getName());
    productToUpdate.setDescription((product.getDescription()));
    productToUpdate.setPrice(product.getPrice());
    productToUpdate.setImageURL(product.getImageURL());
    this.productRepository.save(productToUpdate);
    return productToUpdate;
  }
}
