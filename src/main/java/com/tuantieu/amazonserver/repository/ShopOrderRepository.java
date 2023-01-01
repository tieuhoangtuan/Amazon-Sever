package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {
}
