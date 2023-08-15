package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByid(int id);

    @Query("SELECT o FROM Order o INNER JOIN User u WHERE u.id=?1")
    List<Order> findByUserId(Long id);

    @Query("SELECT o FROM Order o INNER JOIN User u WHERE o.status = 'pending'")
    Order findOrderPendingByUserId(Long userId);

    @Transactional //have to have this annotation here, if not, it will show error: jakarta.persistence.TransactionRequiredException: Executing an update/delete query when update
    @Modifying
    @Query("UPDATE Order o SET o.status = ?2 WHERE o.id = ?1")
    void updateOrderStatus(int id, String newStatus);
}
