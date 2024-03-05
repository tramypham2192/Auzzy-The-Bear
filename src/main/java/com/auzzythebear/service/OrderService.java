package com.auzzythebear.service;

import com.auzzythebear.entity.Order;
import java.util.List;

public interface OrderService {
  Order save(Order order);

  Order update(Order o, int id);

  void delete(int id);

  List<Order> all();

  Order findByid(int id);

  List<Order> findBUserId(Long id);

  Order findOrderPendingByUserId(Long userId);

  void updateOrderStatus(int id, String newStatus);
}
