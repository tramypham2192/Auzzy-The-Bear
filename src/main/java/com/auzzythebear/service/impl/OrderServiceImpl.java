package com.auzzythebear.service.impl;

import com.auzzythebear.entity.Order;
import com.auzzythebear.repository.OrderRepository;
import com.auzzythebear.service.OrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired private OrderRepository orderRepository;

  @Override
  public Order save(Order order) {
    return this.orderRepository.save(order);
  }

  @Override
  public void delete(int id) {
    this.orderRepository.delete(this.orderRepository.findByid(id));
  }

  @Override
  public List<Order> all() {
    return this.orderRepository.findAll();
  }

  @Override
  public Order findByid(int id) {
    return this.orderRepository.findByid(id);
  }

  @Override
  public List<Order> findBUserId(Long id) {
    return this.orderRepository.findByUserId(id);
  }

  @Override
  public Order findOrderPendingByUserId(Long userId) {
    return this.orderRepository.findOrderPendingByUserId(userId);
  }

  @Override
  public void updateOrderStatus(int id, String newStatus) {
    this.orderRepository.updateOrderStatus(id, newStatus);
    System.out.println("update status successfully");
  }

  @Override
  public Order update(Order order, int id) {
    Optional<Order> orderToUpdateOptional = this.orderRepository.findById(id);
    if (!orderToUpdateOptional.isPresent()) {
      return null;
    }
    Order orderToUpdate = orderToUpdateOptional.get();
    orderToUpdate.setCartItemsList(order.getCartItemsList());
    orderToUpdate.setUser((order.getUser()));
    orderToUpdate.setStatus(order.getStatus());
    orderToUpdate.setPayAmount(order.getPayAmount());
    orderToUpdate.setDateAndTimeOfOrder(order.getDateAndTimeOfOrder());
    this.orderRepository.save(orderToUpdate);
    return orderToUpdate;
  }
}
