package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.Exception.OrderNotFoundException;
import com.kreitek.store.application.Exception.ShoppingCartNotFoundException;
import com.kreitek.store.application.Exception.UserNotFoundException;
import com.kreitek.store.application.dto.ItemShoppingCartDTO;
import com.kreitek.store.application.dto.OrderDTO;
import com.kreitek.store.application.dto.OrderInsertDTO;
import com.kreitek.store.application.mapper.OrderMapper;
import com.kreitek.store.application.mapper.ShoppingCartMapper;
import com.kreitek.store.application.service.service.OrderService;
import com.kreitek.store.domain.entity.Order;
import com.kreitek.store.domain.entity.ShoppingCart;
import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistence.OrderPersistence;
import com.kreitek.store.domain.persistence.ShoppinCartPersistence;
import com.kreitek.store.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderPersistence orderPersistence;
    private final UserPersistence userPersistence;
    private final ShoppinCartPersistence shoppinCartPersistence;
    private final OrderMapper orderMapper;
    private final ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public OrderServiceImpl(OrderPersistence orderPersistence, UserPersistence userPersistence, ShoppinCartPersistence shoppinCartPersistence, OrderMapper orderMapper, ShoppingCartMapper shoppingCartMapper) {
        this.orderPersistence = orderPersistence;
        this.userPersistence = userPersistence;
        this.shoppinCartPersistence = shoppinCartPersistence;
        this.orderMapper = orderMapper;

        this.shoppingCartMapper = shoppingCartMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrdersByUser(String userNick) {
        List<User> user = this.userPersistence.getUserByNick(userNick);
        if(!user.isEmpty()){
            List<Order> order = this.orderPersistence.getOrderByUser(user.get(0));
            return orderMapper.toDto(order);
        }else{
            throw new UserNotFoundException("Usuario no econtrado"+userNick);
        }
    }

    @Override
    public void deleteOrdersById(Long orderId) {
        this.orderPersistence.deleteOrdersById(orderId);
    }

    @Override
    @Transactional
    public void addOrderByUser(String userNick, OrderInsertDTO orderInsertDTO) {
        List<User> user = this.userPersistence.getUserByNick(userNick);
        List<ShoppingCart> shoppingCartList = this.shoppinCartPersistence.getShoppingCartByList(orderInsertDTO.getCarritos());
        if(!user.isEmpty()){
            Order order = new Order();
            order.setUser(user.get(0));
            order.setOrderDate(orderInsertDTO.getOrderDate());
            order.setSent(false);
            order.setShippingAddress(orderInsertDTO.getShippingAddress());
            for(ShoppingCart carrito : shoppingCartList){
                order.getCarritos().add(carrito);
            }
            this.orderPersistence.saveOrder(order);
            Long id=order.getId();
            System.out.println("Order Id ñññ: "+id);
            for(ShoppingCart carrito : shoppingCartList){
                carrito.setOrder(order);
                this.shoppinCartPersistence.saveShoppingCart(carrito);
            }
        }else{
            throw new UserNotFoundException("Usuario no econtrado"+userNick);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemShoppingCartDTO> getAllOrdersByUserAndOrderId(String userNick, Long orderId) {
        List<User> user = this.userPersistence.getUserByNick(userNick);
        if(!user.isEmpty()){
            List<Order> order = this.orderPersistence.getOrderByUserAndId(user.get(0),orderId);
            if(!order.isEmpty()){
                return this.shoppingCartMapper.shoppingCartToItemCartDTO(order.get(0).getCarritos());
            }else {
                throw new OrderNotFoundException("Pedido no econtrado"+orderId);
            }
        }else{
            throw new UserNotFoundException("Usuario no econtrado"+userNick);
        }
    }

    /**@Override
    @Transactional
    public void removeShoppingCartFromOrder(Long orderId, Long shoppingCartId) {
        Optional<Order> order = this.orderPersistence.getOrderById(orderId);
        if(order.isPresent()){
            Optional<ShoppingCart> shoppingCart = this.shoppinCartPersistence.getShoppingCartById(shoppingCartId);
            if(shoppingCart.isPresent()){
                Order order1 = order.get();
                order1.getCarritos().remove(shoppingCart.get());
                this.orderPersistence.saveOrder(order1);
            }else{
                throw new ShoppingCartNotFoundException("Carrito no encontrado "+shoppingCartId);
            }
        }else{
            throw new OrderNotFoundException("Pedido no econtrado "+orderId);
        }
    }*/
    //Porque las operaciones de many to one no los hace correctamente? Me toca borrar directamente del ShoppingCart
    @Override
    @Transactional
    public void removeShoppingCartFromOrder(Long orderId, Long shoppingCartId) {
        this.shoppinCartPersistence.deleteById(shoppingCartId);
    }

}
