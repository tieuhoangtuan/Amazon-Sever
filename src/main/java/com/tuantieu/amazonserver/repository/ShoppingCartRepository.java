package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.ShoppingCart;
import com.tuantieu.amazonserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    ShoppingCart findOneByUser(User user);
}
