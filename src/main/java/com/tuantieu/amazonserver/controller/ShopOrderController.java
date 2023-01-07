package com.tuantieu.amazonserver.controller;

import com.tuantieu.amazonserver.dto.OrderDto;
import com.tuantieu.amazonserver.dto.ResponseObject;
import com.tuantieu.amazonserver.entity.ShopOrder;
import com.tuantieu.amazonserver.service.impl.ShopOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/amazon/order")
public class ShopOrderController {
    private final ShopOrderServiceImpl shopOrderServiceImpl;

    @Autowired
    public ShopOrderController(ShopOrderServiceImpl shopOrderServiceImpl) { this.shopOrderServiceImpl = shopOrderServiceImpl; }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ShopOrder> OrderList(){
        return shopOrderServiceImpl.shopOrderList();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDto orderDto, Long userId){
        return shopOrderServiceImpl.placeOrder(orderDto, userId);
    }

    @PutMapping("/{orderId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> updateOrderStatus(@PathVariable Long orderId, Long orderStatusId){
        return shopOrderServiceImpl.updateOrderStatus(orderId, orderStatusId);
    }
}