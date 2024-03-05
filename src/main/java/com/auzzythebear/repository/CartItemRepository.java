package com.auzzythebear.repository;

import com.auzzythebear.entity.CartItem;
import com.auzzythebear.entity.Product;
import com.auzzythebear.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
  List<CartItem> findByUser(User user);

  @Query(
      "SELECT p from Product p inner join CartItem c group by p.id order by sum(c.productQuantity)"
          + " LIMIT 3")
  List<Product> topThreeMostOrderedProduct();
}
