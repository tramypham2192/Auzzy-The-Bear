//package com.example.registrationlogindemo.controller;
//
//import com.example.registrationlogindemo.dto.AddToCartDto;
//import com.example.registrationlogindemo.entity.CartCost;
//import com.example.registrationlogindemo.entity.Product;
//import com.example.registrationlogindemo.exceptions.AuthenticationFailException;
//import com.example.registrationlogindemo.exceptions.ProductNotExistException;
//import com.example.registrationlogindemo.service.CartService;
//import com.example.registrationlogindemo.service.ProductService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/cart")
//public class CartController {
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
//        authenticationService.authenticate(token);
//
//        int userId = authenticationService.getUser(token).getId();
//
//        cartService.addToCart(addToCartDto,userId);
//
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
//
//    }
//    @GetMapping("/")
//    public ResponseEntity<CartCost> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
//        authenticationService.authenticate(token);
//        int userId = authenticationService.getUser(token).getId();
//        CartCost cartCost = cartService.listCartItems(userId);
//        return new ResponseEntity<CartCost>(cartCost,HttpStatus.OK);
//    }
//    @PutMapping("/update/{cartItemId}")
//    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
//                                                      @RequestParam("token") String token) throws AuthenticationFailException,ProductNotExistException {
//        authenticationService.authenticate(token);
//        int userId = authenticationService.getUser(token).getId();
//
//        Product product = productService.findById(cartDto.getProductId());
//
//        cartService.updateCartItem(cartDto,userId,product);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{cartItemId}")
//    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,@RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
//        authenticationService.authenticate(token);
//
//        int userId = authenticationService.getUser(token).getId();
//
//        cartService.deleteCartItem(itemID,userId);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
//    }
//
//}