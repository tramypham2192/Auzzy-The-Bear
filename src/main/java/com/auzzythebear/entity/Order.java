package com.auzzythebear.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id = 0;

  Integer payAmount;

  // this annotation is to avoid this error: Could not determine recommended JdbcType for hash map
  LocalDateTime dateAndTimeOfOrder = LocalDateTime.now();

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
  private Set<CartItem> cartItemsList =
      new HashSet<>(); // use Set<CartItem> instead of HashSet<CartItem> to avoid the error can not
  // can not set ...HashSet to org.hibernate.collection.spi.PersistentSet

  private String status;

  public Order(int id) {
    this.id = id;
  }
}
