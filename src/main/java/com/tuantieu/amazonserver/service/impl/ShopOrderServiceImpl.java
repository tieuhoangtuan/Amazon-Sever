package com.tuantieu.amazonserver.service.impl;

import com.tuantieu.amazonserver.dto.OrderDto;
import com.tuantieu.amazonserver.dto.ResponseObject;
import com.tuantieu.amazonserver.entity.*;
import com.tuantieu.amazonserver.repository.*;
import com.tuantieu.amazonserver.service.ShopOrderService;
import jakarta.persistence.criteria.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ShopOrderRepository shopOrderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
    public ResponseEntity<?> placeOrder(@NotNull OrderDto orderDto, Long userId) {
        User user = userRepository.findOneById(userId);
        ShoppingCart cart = shoppingCartRepository.findOneByUser(user);
        List<CartItem> cartItemList = cartItemRepository.findAllByCart(cart);

        ShopOrder newOrder = new ShopOrder();
        newOrder.setAddress(orderDto.getAddress());
        newOrder.setNote(orderDto.getNote());
        newOrder.setUser(user);
        shopOrderRepository.save(newOrder);

        for(CartItem cartItem : cartItemList){
            OrderItem orderItem = new OrderItem();
            orderItem.setShopOrder(newOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());

            orderItemRepository.save(orderItem);
        }
        shoppingCartRepository.delete(cart);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully placed the order"));
    }

    @Override
    public List<ShopOrder> shopOrderList(){
        return shopOrderRepository.findAll();
    }

    @Override
    public ResponseEntity<ResponseObject> updateOrderStatus(Long orderId, Long orderStatusId) {
        ShopOrder order = shopOrderRepository.findOneById(orderId);
        OrderStatus newOrderStatus = orderStatusRepository.findOneById(orderStatusId);
        order.setOrderStatus(newOrderStatus);
        shopOrderRepository.save(order);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update order status successful"));
    }
}
