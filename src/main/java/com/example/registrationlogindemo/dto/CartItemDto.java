package com.example.registrationlogindemo.dto;
//
//import com.example.registrationlogindemo.entity.Cart;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//
public class CartItemDto {

//    @Column(nullable=false)
    private int productID;

//    @Column(nullable=false)
    private int productQuantity;

    public CartItemDto() {
    }

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
