package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Order;
import org.aspectj.weaver.ast.Or;

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
