package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
