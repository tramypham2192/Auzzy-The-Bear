//package com.example.registrationlogindemo.dto;
//
//import com.example.registrationlogindemo.entity.Cart;
//import com.example.registrationlogindemo.entity.Product;
//import jakarta.validation.constraints.NotNull;
//
//public class CartDto {
//    private Integer id;
//    private @NotNull Integer userId;
//    private @NotNull Integer quantity;
//    private @NotNull Product product;
//
//    public CartDto() {
//    }
//
//    public CartDto(Cart cart) {
//        this.setId(cart.getId());
//        this.setUserId(cart.getUserId());
//        this.setQuantity(cart.getQuantity());
//        this.setProduct(cart.getProduct());
//    }
//
//    @Override
//    public String toString() {
//        return "CartDto{" +
//                "id=" + id +
//                ", userId=" + userId +
//                ", quantity=" + quantity +
//                ", productName=" + product.getName() +
//                '}';
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//}
