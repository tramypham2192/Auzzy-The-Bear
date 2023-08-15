package com.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = " CartItem")

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemID;

    private int productID;
    // if not set up the relationship between Product table and CartItem table, we can retrieve product
    //info from Product table with the product id inside CartItem table. But in order to cross-check input for these two tables,
    // we should set up the relationship between Product table and Cart Item table. In this project, I will just use the productID
    //to retrieve product info, not setting up the relationship
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "cartItem")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable=false)
    private int productQuantity;

    public CartItem(int cartItemID, int productID, Product product, User user, int productQuantity) {
    }

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "cart_id")
//    private Cart cart;
}




