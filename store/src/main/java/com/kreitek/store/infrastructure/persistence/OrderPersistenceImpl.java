package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.domain.entity.Order;
import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistence.OrderPersistence;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderPersistenceImpl implements OrderPersistence {
    private final OrderRepository repository;

    @Autowired
    public OrderPersistenceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> getOrderByUser(User user) {
        List<Order> orders=this.repository.findAllByUser(user);
        return  orders;
    }

    @Override
    public void deleteOrdersById(Long orderId) {
        this.repository.deleteById(orderId);
    }

    @Override
    public void saveOrder(Order order) {
        this.repository.save(order);
    }

    @Override
    public List<Order> getOrderByUserAndId(User user,Long id) {
        return this.repository.findAllByUserAndId(user,id);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return this.repository.findById(orderId);
    }
}
