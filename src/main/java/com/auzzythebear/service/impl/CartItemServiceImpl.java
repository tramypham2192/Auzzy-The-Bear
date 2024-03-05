package com.auzzythebear.service.impl;

import com.auzzythebear.entity.CartItem;
import com.auzzythebear.entity.Product;
import com.auzzythebear.exceptions.ProductNotExistException;
import com.auzzythebear.repository.CartItemRepository;
import com.auzzythebear.service.CartItemService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
  private final CartItemRepository cartItemRepository;

  public CartItemServiceImpl(@Autowired CartItemRepository itemRepository) {
    this.cartItemRepository = itemRepository;
  }

  @Override
  public CartItem save(CartItem item) {
    return this.cartItemRepository.save(item);
  }

  @Override
  public void delete(int itemId) {
    this.cartItemRepository.delete(this.findById(itemId));
  }

  @Override
  public List<CartItem> all() {
    return (List<CartItem>) this.cartItemRepository.findAll();
  }

  @Override
  public CartItem findById(int itemId) {
    Optional<CartItem> cartItemToFindOptional = this.cartItemRepository.findById(itemId);
    if (!cartItemToFindOptional.isPresent()) {
      throw new ProductNotExistException("CartItem id is invalid " + itemId);
    }
    CartItem cartItemtToFind = cartItemToFindOptional.get();
    return cartItemtToFind;
  }

  @Override
  public CartItem update(CartItem item) {
    Optional<CartItem> cartItemToFindOptional =
        this.cartItemRepository.findById(item.getCartItemID());
    if (!cartItemToFindOptional.isPresent()) {
      throw new ProductNotExistException("CartItem id is invalid " + item.getCartItemID());
    }
    CartItem cartItemtToFind = cartItemToFindOptional.get();
    cartItemtToFind.setCartItemID(item.getCartItemID());
    cartItemtToFind.setUser(item.getUser());
    cartItemtToFind.setProductQuantity(item.getProductQuantity());
    cartItemtToFind.setProductID(item.getProductID());
    this.cartItemRepository.save(cartItemtToFind);
    return cartItemtToFind;
  }

  @Override
  public List<Product> topThreeMostOrderedProduct() {
    return cartItemRepository.topThreeMostOrderedProduct();
  }
}
