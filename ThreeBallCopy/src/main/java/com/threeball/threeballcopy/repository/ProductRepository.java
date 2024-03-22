package com.threeball.threeballcopy.repository;

import com.threeball.threeballcopy.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM products where if (?1 is not null, lower(name) like concat(lower (?1), '%'), true)")
    List<Products> searchProduct(String name);
}
