package com.auzzythebear.controller;

import com.auzzythebear.dto.CartItemDto;
import com.auzzythebear.dto.CartItemProductDto;
import com.auzzythebear.dto.UserDto;
import com.auzzythebear.entity.CartItem;
import com.auzzythebear.entity.Order;
import com.auzzythebear.entity.Product;
import com.auzzythebear.entity.Role;
import com.auzzythebear.entity.User;
import com.auzzythebear.service.impl.CartItemServiceImpl;
import com.auzzythebear.service.impl.OrderServiceImpl;
import com.auzzythebear.service.impl.ProductServiceMySQL;
import com.auzzythebear.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
  private final ProductServiceMySQL productServiceMySQL;
  private final CartItemServiceImpl cartItemService;
  private final OrderServiceImpl orderServiceImpl;

  Logger logger = LoggerFactory.getLogger(ProductController.class);

  private UserServiceImpl userService;

  public AuthController(
      @Autowired ProductServiceMySQL productServiceMySQL,
      @Autowired CartItemServiceImpl itemService,
      @Autowired CartItemServiceImpl cartItemService,
      @Autowired OrderServiceImpl orderServiceImpl,
      @Autowired UserServiceImpl userService) {
    this.productServiceMySQL = productServiceMySQL;
    this.cartItemService = cartItemService;
    this.orderServiceImpl = orderServiceImpl;
    this.userService = userService;
  }
  // ------------------------------FOR BOTH ADMIN AND USER
  // ROLE---------------------------------------------------------//
  @GetMapping("/index")
  public String home(Principal principal) {
    String username = principal.getName();
    User user = userService.findByEmail(username);
    System.out.println("this is " + user.getRoles().get(0).getName());
    if (user.getRoles().get(0).getName().equals("ROLE_ADMIN")) {
      return "redirect:/adminHomePage";
    }
    return "redirect:/indexUser";
  }

  @GetMapping("/indexUser")
  public String indexUser(Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    System.out.println(
        "call index get mapping, " + userService.findByEmail(authentication.getName()).getName());
    return "indexUser";
  }

  @GetMapping("/aboutUs-contactUs/about")
  public String about(Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    return "aboutUs-contactUs/about";
  }

  @GetMapping("/aboutUs-contactUs/contactUs")
  public String contactUs(Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    return "aboutUs-contactUs/contactUs";
  }

  // handler method to handle user registration request
  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    System.out.println("calling get mapping for register");
    UserDto user = new UserDto();
    model.addAttribute("user", user);
    return "register";
  }

  // handler method to handle register user form submit request
  @PostMapping("/register/save")
  public String registration(
      @Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
    System.out.println("register/save called");
    User existing = userService.findByEmail(user.getEmail());
    if (existing != null) {
      result.rejectValue("email", null, "There is already an account registered with that email");
    }
    if (result.hasErrors()) {
      model.addAttribute("user", user);
      return "register";
    }
    userService.saveUser(user);
    return "redirect:/register?success";
  }

  @GetMapping("/login")
  public String loginForm(Model model) {
    System.out.println("@getmapping(/login) called");
    User user = new User();
    model.addAttribute("user", user);
    return "login";
  }

  @GetMapping("/success")
  public String getSuccessPage(Principal principal) {
    String username = principal.getName();
    User user = userService.findByEmail(username);
    System.out.println(user.getRoles().get(0).getName());
    for (Role r : user.getRoles()) {
      if (r.getName().equals("ROLE_ADMIN")) {
        return "redirect:/adminHomePage";
      }
    }
    return "redirect:/product/productList";
  }

  // ------------------------------UPLOAD
  // IMAGES---------------------------------------------------------//

  @GetMapping("/uploadForm")
  public String indexPage() {
    return "uploadForm";
  }

  @GetMapping("image/imageName")
  @ResponseBody
  public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {

    File serverFile = new File("/home/user/uploads/" + imageName + ".jpg");

    return Files.readAllBytes(serverFile.toPath());
  }

  // ------------------------------DISPLAY
  // PRODUCTS---------------------------------------------------------//

  @GetMapping("/product/displayProductInCard")
  public String displayProductInCard() {
    System.out.println("displayProductInCard @getmapping called");
    return "product/displayProductInCard";
  }

  @GetMapping("/product/productList")
  public String productList(Model model, Authentication authentication) {
    model.addAttribute("productList", productServiceMySQL.all());
    logger.info("size: {}", productServiceMySQL.all().size());
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    return "product/productList";
  }

  @GetMapping("/product/productListWithKeywordSearch/{keyword}")
  public String productList(
      @Param("keyword") String keyword, Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    model.addAttribute(
        "productList", productServiceMySQL.listProductsAccordingToSearchKeyword(keyword));
    model.addAttribute("keyword", keyword);
    return "product/productListWithKeywordSearch";
  }

  @GetMapping("/product/sortProductsFromCheapestToMostExpensive")
  public String cheapestProducts(Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    model.addAttribute("productList", productServiceMySQL.listCheapestProducts());
    return "product/sortProductsFromCheapestToMostExpensive";
  }

  @GetMapping("/product/sortProductsAccordingToMostOrdered")
  public String sortProductsAccordingToMostOrdered(Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    model.addAttribute("productList", cartItemService.topThreeMostOrderedProduct());
    return "product/sortProductsAccordingToMostOrdered";
  }

  // ---------------------------------------FOR USER ROLE
  // ONLY---------------------------------------------------------//

  // my pham
  @CrossOrigin()
  @PostMapping("/add-to-cart-item")
  public String addItemToCart(
      @RequestBody CartItemDto cartItemDto,
      Principal principal,
      HttpServletRequest request,
      Model model,
      Authentication authentication)
      throws Exception {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    if (principal == null) {
      return "redirect:/login";
    }
    String username = principal.getName();
    User user = userService.findByEmail(username);

    // IF THIS USER HAS A PENDING ORDER
    List<Order> ordersOfThisUser = orderServiceImpl.findBUserId(user.getId());
    if (ordersOfThisUser.size() > 0) {
      for (Order o : ordersOfThisUser) {
        if (o.getStatus().equals("pending")) {
          CartItem cartItemFound = new CartItem();
          int payAmount = 0;
          for (CartItem c : o.getCartItemsList()) {
            // Case 1: user has an existing cart item with this product => change quantity, change
            // pay amount
            if (c.getProductID() == cartItemDto.getProductID()) {
              cartItemFound = this.cartItemService.findById(cartItemDto.getProductID());

              // update product quantity in this existing cart item
              cartItemFound.setProductQuantity(cartItemDto.getProductQuantity());

              // calculate pay amount after changing
              payAmount +=
                  cartItemFound.getProductQuantity()
                      * productServiceMySQL.findById(cartItemFound.getProductID()).getPrice();
              // update Order for cartItemFound
              cartItemFound.setOrder(o);
              this.cartItemService.update(cartItemFound);
            }
            // Case 2: user do not have an existing cart item with this product => new cart item,
            // -> add this new cart item into existing cart item list
            // -> update cart item list for the pending order, change pay amount
            for (CartItem ci : o.getCartItemsList()) {
              System.out.println(ci.getProductID());
            }
            CartItem newCartItem = new CartItem();
            newCartItem.setCartItemID(cartItemDto.getProductID());
            newCartItem.setProductID(cartItemDto.getProductID());
            newCartItem.setUser(user);
            newCartItem.setProductQuantity(cartItemDto.getProductQuantity());
            // update cart item list for the pending order
            newCartItem.setOrder(o);
            o.getCartItemsList().add(newCartItem);

            // set properties for new Order
            o.setCartItemsList(o.getCartItemsList());

            // calculate pay amount after changing
            for (CartItem cartItem : o.getCartItemsList()) {
              System.out.println(
                  "quantity: "
                      + cartItem.getProductQuantity()
                      + "price: "
                      + productServiceMySQL.findById(cartItem.getProductID()).getPrice());
              payAmount +=
                  cartItem.getProductQuantity()
                      * productServiceMySQL.findById(cartItem.getProductID()).getPrice();
            }
            // update pay amount for the pending order
            o.setPayAmount(payAmount);
            // save the changes of new cart item and order
            orderServiceImpl.update(o, o.getId());
            cartItemService.save(newCartItem);
            return "/product/productList";
          }
        }
      }
    }
    // IF USER NEVER HAD ORDER BEFORE OR ALL HIS/HER ORDERS ARE COMPLETED
    CartItem newCartItem = new CartItem();
    Order newOrder = new Order();
    // new Order
    // calculate id for new order
    //            Calendar cal = Calendar.getInstance();
    //            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    //            int newOrder_id = (int) (user.getId() + dayOfMonth);
    // if this order id already exist (user order for the second, third time during the same day,
    // double the string to have the new id
    //            if (orderServiceImpl.findByid(newOrder_id) != null) {
    //                newOrder_id += newOrder_id + orderServiceImpl.all().size();
    //            }
    // creat new CartItemList
    HashSet<CartItem> cartItemsList = new HashSet<>();
    // add newCartItem into cartItemList
    // new CartItem
    // set properties for new CartItem
    //            newCartItem.setCartItemID(cartItemDto.getProductID());
    newCartItem.setProductID(cartItemDto.getProductID());
    newCartItem.setUser(user);
    newCartItem.setProductQuantity(cartItemDto.getProductQuantity());
    newCartItem.setOrder(newOrder);
    cartItemsList.add(newCartItem);

    // set properties for new Order
    newOrder.setCartItemsList(cartItemsList);
    //            newOrder.setId(newOrder_id);
    newOrder.setUser(user);
    newOrder.setDateAndTimeOfOrder(LocalDateTime.now());
    int totalAmount = 0;

    for (CartItem c : newOrder.getCartItemsList()) {
      System.out.println(c.getProductID());
      totalAmount =
          c.getProductQuantity() * this.productServiceMySQL.findById(c.getProductID()).getPrice();
    }
    newOrder.setPayAmount(totalAmount);
    newOrder.setStatus("pending");

    // save new order
    this.orderServiceImpl.save(newOrder);
    // save new cart item
    this.cartItemService.save(
        newCartItem); // this line must be here, not on line 272 because it will cause error save
    // item before flushing ...
    return "/product/productList";
  }

  @GetMapping("/add-to-cart-item")
  public String cartItemDisplay(Principal principal, Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    //        List<CartItem> cartItems = this.cartItemService.all();
    List<CartItemProductDto> cartItemProductDtoList = new ArrayList<>();
    // get customer's name
    String username = principal.getName();
    User user = userService.findByEmail(username);
    model.addAttribute("customerName", user.getName());
    // find list of cart item list in pending orders to render to add-to-cart-item page
    Order pendingOrder = orderServiceImpl.findOrderPendingByUserId(user.getId());

    if (pendingOrder != null) {
      for (CartItem c : pendingOrder.getCartItemsList()) {
        Product p = this.productServiceMySQL.findById(c.getProductID());
        CartItemProductDto cartItemProductDto =
            new CartItemProductDto(
                c.getProductID(), p.getName(), p.getPrice(), c.getProductQuantity());
        cartItemProductDtoList.add(cartItemProductDto);
      }
    }
    model.addAttribute("cartItemProductDtoList", cartItemProductDtoList);
    // get add to cart date
    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDate = myDateObj.format(myFormatObj);
    model.addAttribute("date", formattedDate);
    logger.info("cartItemProductDtoList size: {}", cartItemProductDtoList.size());
    return "add-to-cart-item";
  }

  @PostMapping("/checkout")
  public String PostMappingCheckOut(Principal principal) {
    // find user
    String username = principal.getName();
    User user = userService.findByEmail(username);
    // get pending order
    Order pendingOrder = orderServiceImpl.findOrderPendingByUserId(user.getId());
    // change status of pending order to completed
    // this.orderServiceImpl.findBUserId(user.getId()).setStatus("completed"); //have to use update
    // method, not setter
    this.orderServiceImpl.updateOrderStatus(pendingOrder.getId(), "completed");
    pendingOrder.setStatus("completed");
    return "redirect:/orderCompleted";
  }

  @GetMapping("/checkout")
  public String checkout(Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    return "checkout";
  }

  @GetMapping("/orderCompleted")
  public String orderCompleted(Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    return "orderCompleted";
  }

  // ---------------------------------------FOR ADMIN ROLE
  // ONLY---------------------------------------------------------//

  @GetMapping("/adminHomePage")
  public String listRegisteredUsers(Model model) {
    List<UserDto> users = userService.findAllUsers();
    model.addAttribute("users", users);
    return "adminHomePage";
  }

  @GetMapping("/product/addProduct")
  public String addProductPage(Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    return "product/addProduct";
  }

  @GetMapping("/product/editProduct")
  public String editProduct(Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    return "/product/editProduct";
  }

  @GetMapping("product/editProduct/{productId}")
  public String editProductPage(Model model, @PathVariable("productId") int productId) {
    model.addAttribute("product", productServiceMySQL.findById(productId));
    return "product/editProduct";
  }

  @GetMapping("/product/deleteProduct")
  public String deleteProductPage(Model model, Authentication authentication) {
    model.addAttribute("userName", userService.findByEmail(authentication.getName()).getName());
    return "product/deleteProduct";
  }
}
