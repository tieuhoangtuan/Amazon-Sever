package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<CartItem, Long> {
}
