package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.domain.entity.Order;
import com.kreitek.store.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUser(User user);

    List<Order> findAllByUserAndId(User user, Long id);
}
