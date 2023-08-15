package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findByid(int id);
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.price) LIKE %?1%")
    public List<Product> search(String keyword);

    @Query("SELECT p FROM Product p ORDER BY (p.price)")
    public List<Product> sortProductsFromCheapestToMostExpensive();
}
