package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.CartItem;
import com.example.registrationlogindemo.entity.Product;
import com.example.registrationlogindemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUser(User user);
    @Query("SELECT p from Product p inner join CartItem c group by p.id order by sum(c.productQuantity) LIMIT 3")
    List<Product> topThreeMostOrderedProduct();
}