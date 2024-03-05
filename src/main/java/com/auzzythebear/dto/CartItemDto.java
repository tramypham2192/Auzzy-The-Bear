package com.auzzythebear.dto;
//
// import com.example.registrationlogindemo.entity.Cart;
import jakarta.persistence.*;

//
public class CartItemDto {

  //    @Column(nullable=false)
  private int productID;

  //    @Column(nullable=false)
  private int productQuantity;

  public CartItemDto() {}

  public CartItemDto(int productID, int productQuantity) {
    this.productID = productID;
    this.productQuantity = productQuantity;
  }

  public int getProductID() {
    return productID;
  }

  public int getProductQuantity() {
    return productQuantity;
  }
}
