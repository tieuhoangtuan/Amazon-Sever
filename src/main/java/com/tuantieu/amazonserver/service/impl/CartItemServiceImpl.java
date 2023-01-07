package com.tuantieu.amazonserver.service.impl;

import com.tuantieu.amazonserver.dto.AddToCartDto;
import com.tuantieu.amazonserver.dto.ResponseObject;
import com.tuantieu.amazonserver.entity.*;
import com.tuantieu.amazonserver.repository.CartItemRepository;
import com.tuantieu.amazonserver.repository.ProductRepository;
import com.tuantieu.amazonserver.repository.ShoppingCartRepository;
import com.tuantieu.amazonserver.repository.UserRepository;
import com.tuantieu.amazonserver.service.CartItemService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<?> addToCart(@NotNull AddToCartDto addToCartDto, Long userId) {
        Product existProduct = productRepository.findOneById(addToCartDto.getProductId());

        // Kiểm tra xem product có tồn tại không?
        if(existProduct == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Product does not exist"));
        }

        User user = userRepository.findOneById(userId);
        ShoppingCart cart = shoppingCartRepository.findOneByUser(user);
        List<CartItem> cartItemList = cartItemRepository.findAllByCart(cart);

        if(cart != null) {
            for(CartItem cartItem : cartItemList){
                if(cartItem.getProduct().getId().equals(addToCartDto.getProductId())){
                    int quantity = cartItem.getQuantity() + addToCartDto.getQuantity();
                    Double price = quantity * cartItem.getPrice();

                    cartItem.setQuantity(quantity);
                    cartItem.setPrice(price);
                    cartItemRepository.save(cartItem);
                }
            }
        } else {
            ShoppingCart newCart = new ShoppingCart();
            newCart.setUser(user);
            shoppingCartRepository.save(newCart);

            CartItem cartItem = new CartItem();
            cartItem.setProduct(existProduct);
            cartItem.setQuantity(addToCartDto.getQuantity());
            cartItem.setCart(newCart);
            cartItem.setPrice(addToCartDto.getQuantity()* existProduct.getPrice());
            cartItemRepository.save(cartItem);

        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Add to cart successfully"));
    }

    @Override
    public ResponseEntity<ResponseObject> getCartItemList(Long userId) {
        User user = userRepository.findOneById(userId);
        ShoppingCart existCart = shoppingCartRepository.findOneByUser(user);
        if(existCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Shopping cart does not exist"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Get cart item list successful", cartItemRepository.findAllByCart(existCart)));
    }
}
