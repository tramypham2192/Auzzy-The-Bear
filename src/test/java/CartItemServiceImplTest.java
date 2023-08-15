import com.example.registrationlogindemo.RegistrationLoginDemoApplication;
import com.example.registrationlogindemo.entity.Product;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.CartItemRepository;
import com.example.registrationlogindemo.entity.CartItem;
import com.example.registrationlogindemo.service.CartItemService;
import com.example.registrationlogindemo.service.impl.CartItemServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = RegistrationLoginDemoApplication.class)
@SpringBootTest
public class CartItemServiceImplTest {
    @MockBean
    private CartItemRepository cartItemRepository;
    private CartItemServiceImpl cartItemService;
    private CartItem cartItem = new CartItem();

    @Test
    void saveCartItem(){
        givenCartItemDataAreAvailable();
        whenCreateNewCartItemAndSave();
        thenCreateNewCartItemAndReturnIt();
    }

    void givenCartItemDataAreAvailable(){
        Mockito.when(cartItemRepository.save(cartItem)).thenReturn(cartItem);
        cartItemService = new CartItemServiceImpl(cartItemRepository);
    }
    void whenCreateNewCartItemAndSave(){
        cartItem.setCartItemID(1);
        cartItem.setUser(new User());
        cartItem.setProductQuantity(7);
        cartItem.setProductID(1);
        cartItem.setProduct(new Product(3, "Matcha Latte", 8, "Very healthy", "http://localhost:8080/images/upload-dir/Latte.jpg"));
    }

    void thenCreateNewCartItemAndReturnIt(){
        CartItem savedCartItem = cartItemService.save(cartItem);
        assertEquals(cartItem, savedCartItem);
    }
}