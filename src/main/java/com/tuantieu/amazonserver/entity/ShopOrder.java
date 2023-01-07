package com.tuantieu.amazonserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop_order")
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "shopOrder")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;

    private String address;

    private String note;

    private Long total;
}
