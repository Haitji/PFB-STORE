package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.application.dto.OrderInsertDTO;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.entity.ShoppingCart;
import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistence.ShoppinCartPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShoppinCartPersistenceImpl implements ShoppinCartPersistence {

    private final ShoppingCartRepository repository;

    @Autowired
    public ShoppinCartPersistenceImpl(ShoppingCartRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ShoppingCart> GetShoppingCartByUser(User user) {
        List<ShoppingCart> shoppingCarts = repository.findByUserAndOrder(user,null);
        return shoppingCarts;
    }

    @Override
    public void saveShoppingCart(ShoppingCart shoppingCart) {
        this.repository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartByUserAndItem(User user1, Item item) {
        return this.repository.findByUserAndItemAndOrder(user1,item,null);
    }

    @Override
    public void deleteItemOnShoppingCartByUser(User user1, Item item) {
        this.repository.deleteByUserAndItem(user1,item);
    }

    @Override
    public List<ShoppingCart> getShoppingCartByList(List<Long> orderInsertDTO) {
        return repository.findAllByIdIn(orderInsertDTO);
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartById(Long shoppingCartId) {
        return this.repository.findById(shoppingCartId);
    }

    @Override
    public void deleteById(Long shoppingCartId) {
        this.repository.deleteById(shoppingCartId);
    }
}
