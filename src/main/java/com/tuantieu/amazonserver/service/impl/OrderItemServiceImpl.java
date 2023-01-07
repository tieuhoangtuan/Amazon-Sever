package com.tuantieu.amazonserver.service.impl;

import com.tuantieu.amazonserver.entity.OrderItem;
import com.tuantieu.amazonserver.entity.ShopOrder;
import com.tuantieu.amazonserver.repository.OrderItemRepository;
import com.tuantieu.amazonserver.repository.ShopOrderRepository;
import com.tuantieu.amazonserver.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    ShopOrderRepository shopOrderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;


    @Override
    public List<OrderItem> getOrderItemListByOrderId(Long orderId) {
        ShopOrder shopOrder = shopOrderRepository.findOneById(orderId);
        return orderItemRepository.getOrderItemByShopOrder(shopOrder);
    }
}
