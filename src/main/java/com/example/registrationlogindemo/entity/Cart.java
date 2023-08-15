//package com.example.registrationlogindemo.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.Cascade;
//
//import java.util.Date;
//import java.util.HashSet;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "cart")
//public class Cart
//{
//    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "cart_id")
//    private String id;
//    private int totalAmount;
//    private Date createdDate;
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cart")
//    private HashSet<CartItem> cartItemsList = new HashSet<>();
//
//    public Cart(String id, Date date, User user) {
//    }
//
//    public Cart(String id) {
//        this.id = id;
//    }
//
////    public void add(CartItem item) {
////        if (item != null) {
////            if (cartItemsList == null) {
////                cartItemsList = new HashSet<>();
////            }
////            cartItemsList.add(item);
////        }
////    }
//
////    public int getTotalAmount()
////    {
////        int totalAmount = 0;
////        for (CartItem c : this.cartItemsList)
////        {
//////            totalAmount = totalAmount + c.getProductQuantity() * c.getProductPrice();
////        }
////        return totalAmount;
////    }
//}
