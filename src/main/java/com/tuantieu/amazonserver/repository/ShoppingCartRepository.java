package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
}
