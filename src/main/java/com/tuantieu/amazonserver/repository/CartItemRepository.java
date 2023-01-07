package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.CartItem;
import com.tuantieu.amazonserver.entity.Product;
import com.tuantieu.amazonserver.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart(ShoppingCart shoppingCart);
}
