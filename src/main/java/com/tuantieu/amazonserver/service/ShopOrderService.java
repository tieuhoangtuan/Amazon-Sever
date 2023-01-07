package com.tuantieu.amazonserver.service;

import com.tuantieu.amazonserver.dto.OrderDto;
import com.tuantieu.amazonserver.dto.ResponseObject;
import com.tuantieu.amazonserver.entity.ShopOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShopOrderService {
    ResponseEntity<?> placeOrder(OrderDto orderDto, Long userId);

    List<ShopOrder> shopOrderList();

    ResponseEntity<ResponseObject> updateOrderStatus(Long orderId, Long orderStatusId);
}
