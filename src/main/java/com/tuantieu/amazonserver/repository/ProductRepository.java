package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);

    Product findOneById(Long id);
}
