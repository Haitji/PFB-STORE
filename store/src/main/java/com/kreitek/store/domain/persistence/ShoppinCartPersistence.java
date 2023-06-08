package com.kreitek.store.domain.persistence;

import com.kreitek.store.application.dto.OrderInsertDTO;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.entity.ShoppingCart;
import com.kreitek.store.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface ShoppinCartPersistence {
    List<ShoppingCart> GetShoppingCartByUser(User user);

    void saveShoppingCart(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getShoppingCartByUserAndItem(User user1, Item item);

    void deleteItemOnShoppingCartByUser(User user1, Item item);

    List<ShoppingCart> getShoppingCartByList(List<Long> orderInsertDTO);

    Optional<ShoppingCart> getShoppingCartById(Long shoppingCartId);

    void deleteById(Long shoppingCartId);
}
