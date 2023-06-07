package com.kreitek.store.application.service.service;

import com.kreitek.store.application.dto.ItemShoppingCartDTO;

import java.util.List;

public interface ShoppingCartService {


    List<ItemShoppingCartDTO> getShoppingCartByUser(String userNick);

    void addItemsOnShoppingCart(String userNick, Long id, int units);

    void deleteItemOnShoppingCart(String userNick, Long itemId);
}
