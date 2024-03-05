package com.auzzythebear.exceptions;

public class CartItemNotExistException extends IllegalArgumentException {
  public CartItemNotExistException(String message) {
    super(message);
  }
}
