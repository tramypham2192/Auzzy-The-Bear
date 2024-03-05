package com.auzzythebear.service;

import com.auzzythebear.dto.UserDto;
import com.auzzythebear.entity.User;
import java.util.List;

public interface UserService {
  void saveUser(UserDto userDto);

  User findByEmail(String email);

  User findByID(Long userID);

  List<UserDto> findAllUsers();
}
