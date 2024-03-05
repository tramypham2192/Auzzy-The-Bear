package com.auzzythebear.service.impl;

import com.auzzythebear.dto.UserDto;
import com.auzzythebear.entity.Role;
import com.auzzythebear.entity.User;
import com.auzzythebear.repository.RoleRepository;
import com.auzzythebear.repository.UserRepository;
import com.auzzythebear.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;

  public UserServiceImpl(
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void saveUser(UserDto userDto) {
    User user = new User();
    user.setName(userDto.getFirstName() + " " + userDto.getLastName());
    user.setEmail(userDto.getEmail());

    // encrypt the password once we integrate spring security
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    Role role = roleRepository.findByName("ROLE_USER");
    if (role == null) {
      role = checkRoleExist();
    }
    user.setRoles(Arrays.asList(role));
    userRepository.save(user);
  }

  @Override
  public User findByEmail(String email) {
    System.out.println(
        "findByEmail method inside UserServiceImpl: success, email is "
            + email
            + " object tim duoc la: "
            + userRepository.findByEmail(email));
    return userRepository.findByEmail(email);
  }

  @Override
  public User findByID(Long userID) {
    return userRepository.findByid(userID);
  }

  @Override
  public List<UserDto> findAllUsers() {
    List<User> users = userRepository.findAll();
    System.out.println("findAllUsers method inside UserServiceImpl: success");
    return users.stream().map((user) -> convertEntityToDto(user)).collect(Collectors.toList());
  }

  private UserDto convertEntityToDto(User user) {
    UserDto userDto = new UserDto();
    String[] name = user.getName().split(" ");
    userDto.setFirstName(name[0]);
    userDto.setLastName(name[1]);
    userDto.setEmail(user.getEmail());
    return userDto;
  }

  private Role checkRoleExist() {
    Role role = new Role();
    role.setName("ROLE_USER");
    return roleRepository.save(role);
  }
}
