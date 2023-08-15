import com.example.registrationlogindemo.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findByid(int id);
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.price) LIKE %?1%")
    public List<Product> search(String keyword);

    @Query("SELECT p FROM Product p ORDER BY (p.price)")
    public List<Product> sortProductsFromCheapestToMostExpensive();
}