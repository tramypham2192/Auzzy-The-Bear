package com.auzzythebear.repository;

import com.auzzythebear.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  User findByid(Long userID);
}
