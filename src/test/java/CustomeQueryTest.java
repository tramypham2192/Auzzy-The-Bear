import com.example.registrationlogindemo.RegistrationLoginDemoApplication;
import com.example.registrationlogindemo.entity.CartItem;
import com.example.registrationlogindemo.entity.Product;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.repository.ProductRepository;
import com.example.registrationlogindemo.repository.CartItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@ContextConfiguration(classes = ProductRepository.class)
//@TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=create",
//        "spring.datasource.platform=h2",
//        "spring.jpa.show-sql=true"
//})
//@EnableAutoConfiguration
//@EntityScan(basePackages = {"com.entity"})

@ContextConfiguration(classes = RegistrationLoginDemoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CustomeQueryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public CartItemRepository cartItemRepository;

    @Autowired
    public UserRepository userRepository;

    @Test
    public void shouldFindProductWithKeyword(){
        List<Product> allProductList = (List<Product>) productRepository.findAll();
        List<Product> productList = productRepository.search("Smoothie");
        System.out.println(allProductList.size());
        assertThat(productList.contains(allProductList.get(0)));
    }

    @Test
    public void shouldSortProductsFromCheapestToMostExpensive(){
        List<Product> allProductList = (List<Product>) productRepository.findAll();
        List productListSorted = productRepository.sortProductsFromCheapestToMostExpensive();
        assertEquals(productListSorted.get(0), allProductList.get(6));
        assertEquals(productListSorted.get(productListSorted.size() - 1), allProductList.get(9));
    }

    @Test
    public void shouldFindTopThreeMostOrderedProduct(){
        List<CartItem> allCartItems = cartItemRepository.findAll();
        List<Product> mostOrderedProductList = this.cartItemRepository.topThreeMostOrderedProduct();
        assertEquals(mostOrderedProductList.get(0), allCartItems.get(9));
        assertEquals(mostOrderedProductList.get(1), allCartItems.get(10));
        assertEquals(mostOrderedProductList.get(2), allCartItems.get(11));
    }
}