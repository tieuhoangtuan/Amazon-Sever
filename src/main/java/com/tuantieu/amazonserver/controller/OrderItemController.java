package com.tuantieu.amazonserver.controller;

import com.tuantieu.amazonserver.entity.OrderItem;
import com.tuantieu.amazonserver.service.impl.OrderItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/amazon/orderitem")
public class OrderItemController {
    private final OrderItemServiceImpl orderItemServiceImpl;

    @Autowired
    public OrderItemController(OrderItemServiceImpl orderItemServiceImpl) {
        this.orderItemServiceImpl = orderItemServiceImpl;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<OrderItem> getOrderItemListByOrderId(Long orderId){
        return orderItemServiceImpl.getOrderItemListByOrderId(orderId);
    }
}
