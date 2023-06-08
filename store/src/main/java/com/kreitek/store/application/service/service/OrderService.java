package com.kreitek.store.application.service.service;

import com.kreitek.store.application.dto.ItemShoppingCartDTO;
import com.kreitek.store.application.dto.OrderDTO;
import com.kreitek.store.application.dto.OrderInsertDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrdersByUser(String userNick);

    void deleteOrdersById(Long orderId);

    void addOrderByUser(String userNick, OrderInsertDTO orderInsertDTO);

    List<ItemShoppingCartDTO> getAllOrdersByUserAndOrderId(String userNick, Long orderId);

    void removeShoppingCartFromOrder(Long orderId, Long shoppingCartId);
}
