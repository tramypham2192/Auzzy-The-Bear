//package com.example.registrationlogindemo.service.impl;
//
////import com.example.registrationlogindemo.entity.Cart;
//import com.example.registrationlogindemo.entity.Cart;
//import com.example.registrationlogindemo.entity.CartItem;
//import com.example.registrationlogindemo.entity.Product;
//import com.example.registrationlogindemo.entity.User;
//import com.example.registrationlogindemo.repository.CartItemRepository;
//import com.example.registrationlogindemo.repository.CartRepository;
//import com.example.registrationlogindemo.service.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class CartServiceImpl implements CartService
//{
//    @Autowired
//    private CartRepository cartRepo;
//
//    @Override
//    public Cart save(Cart cart) {
//        return this.cartRepo.save(cart);
//    }
//
//    @Override
//    public void delete(int id) {
//        this.cartRepo.delete(this.cartRepo.findByid(id));
//    }
//
//    @Override
//    public List<Cart> all() {
//        return this.cartRepo.findAll();
//    }
//
//    @Override
//    public Cart findByid(int id) {
//        return this.cartRepo.findByid(id);
//    }
//
//}