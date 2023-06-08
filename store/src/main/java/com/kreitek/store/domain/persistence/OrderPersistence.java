package com.kreitek.store.domain.persistence;

import com.kreitek.store.domain.entity.Order;
import com.kreitek.store.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderPersistence {
    List<Order> getOrderByUser(User user);

    void deleteOrdersById(Long orderId);

    void saveOrder(Order order);

    List<Order> getOrderByUserAndId(User user,Long id);

    Optional<Order> getOrderById(Long orderId);
}
