import static org.junit.jupiter.api.Assertions.assertEquals;

import com.auzzythebear.AuzzyTheBearApplication;
import com.auzzythebear.entity.CartItem;
import com.auzzythebear.entity.Product;
import com.auzzythebear.entity.User;
import com.auzzythebear.repository.CartItemRepository;
import com.auzzythebear.service.impl.CartItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = AuzzyTheBearApplication.class)
@SpringBootTest
public class CartItemServiceImplTest {
  @MockBean private CartItemRepository cartItemRepository;
  private CartItemServiceImpl cartItemService;
  private CartItem cartItem = new CartItem();

  @Test
  void saveCartItem() {
    givenCartItemDataAreAvailable();
    whenCreateNewCartItemAndSave();
    thenCreateNewCartItemAndReturnIt();
  }

  void givenCartItemDataAreAvailable() {
    Mockito.when(cartItemRepository.save(cartItem)).thenReturn(cartItem);
    cartItemService = new CartItemServiceImpl(cartItemRepository);
  }

  void whenCreateNewCartItemAndSave() {
    cartItem.setCartItemID(1);
    cartItem.setUser(new User());
    cartItem.setProductQuantity(7);
    cartItem.setProductID(1);
    cartItem.setProduct(
        new Product(
            3,
            "Matcha Latte",
            8,
            "Very healthy",
            "http://localhost:8080/images/upload-dir/Latte.jpg"));
  }

  void thenCreateNewCartItemAndReturnIt() {
    CartItem savedCartItem = cartItemService.save(cartItem);
    assertEquals(cartItem, savedCartItem);
  }
}
