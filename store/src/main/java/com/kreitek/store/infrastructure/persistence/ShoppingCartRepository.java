package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.entity.ShoppingCart;
import com.kreitek.store.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    List<ShoppingCart> findByUser(User user);

    Optional<ShoppingCart> findByUserAndItem(User user1, Item item);

    void deleteByUserAndItem(User user1, Item item);
}
