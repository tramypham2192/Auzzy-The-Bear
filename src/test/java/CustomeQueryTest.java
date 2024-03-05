import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.auzzythebear.AuzzyTheBearApplication;
import com.auzzythebear.entity.CartItem;
import com.auzzythebear.entity.Product;
import com.auzzythebear.repository.CartItemRepository;
import com.auzzythebear.repository.ProductRepository;
import com.auzzythebear.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

// @ExtendWith(SpringExtension.class)
// @DataJpaTest
// @ContextConfiguration(classes = ProductRepository.class)
// @TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=create",
//        "spring.datasource.platform=h2",
//        "spring.jpa.show-sql=true"
// })
// @EnableAutoConfiguration
// @EntityScan(basePackages = {"com.entity"})

@ContextConfiguration(classes = AuzzyTheBearApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CustomeQueryTest {
  @Autowired private TestEntityManager entityManager;

  @Autowired public ProductRepository productRepository;

  @Autowired public CartItemRepository cartItemRepository;

  @Autowired public UserRepository userRepository;

  @Test
  public void shouldFindProductWithKeyword() {
    List<Product> allProductList = (List<Product>) productRepository.findAll();
    List<Product> productList = productRepository.search("Smoothie");
    System.out.println(allProductList.size());
    assertThat(productList.contains(allProductList.get(0)));
  }

  @Test
  public void shouldSortProductsFromCheapestToMostExpensive() {
    List<Product> allProductList = (List<Product>) productRepository.findAll();
    List productListSorted = productRepository.sortProductsFromCheapestToMostExpensive();
    assertEquals(productListSorted.get(0), allProductList.get(6));
    assertEquals(productListSorted.get(productListSorted.size() - 1), allProductList.get(9));
  }

  @Test
  public void shouldFindTopThreeMostOrderedProduct() {
    List<CartItem> allCartItems = cartItemRepository.findAll();
    List<Product> mostOrderedProductList = this.cartItemRepository.topThreeMostOrderedProduct();
    assertEquals(mostOrderedProductList.get(0), allCartItems.get(9));
    assertEquals(mostOrderedProductList.get(1), allCartItems.get(10));
    assertEquals(mostOrderedProductList.get(2), allCartItems.get(11));
  }
}
