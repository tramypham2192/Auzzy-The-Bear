package com.auzzythebear.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemProductDto {
  private int productID;
  private String productName;
  private int productPrice;

  private int productQuantity;

  public CartItemProductDto(
      int productID, String productName, int productPrice, int productQuantity) {
    this.productID = productID;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productQuantity = productQuantity;
  }
}
