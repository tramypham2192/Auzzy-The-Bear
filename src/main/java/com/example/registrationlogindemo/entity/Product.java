package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {
    @Id
    @Column(name = "productID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private int price;
    @Column(name="description")
    private String description;
    @Column(nullable = true, length = 64)
    private String imageURL;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_item_id", referencedColumnName = "cartItemID")
    private CartItem cartItem;

    public Product(){};
    public Product(int id, String name, int price, String description, String imageURL) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
    }

    public Product(String name, int price, String description, String imageURL) {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
//    @Transient
    public String getImageURL() {
        if (imageURL == null) return null;
        return this.imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
