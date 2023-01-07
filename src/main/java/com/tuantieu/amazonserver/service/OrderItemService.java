package com.tuantieu.amazonserver.service;

import com.tuantieu.amazonserver.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getOrderItemListByOrderId(Long orderId);
}
