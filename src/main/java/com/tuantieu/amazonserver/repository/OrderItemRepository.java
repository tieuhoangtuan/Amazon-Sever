package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.OrderItem;
import com.tuantieu.amazonserver.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> getOrderItemByShopOrder(ShopOrder order);
}
