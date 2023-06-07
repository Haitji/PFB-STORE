package com.kreitek.store.infrastructure.persistence;

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
        List<ShoppingCart> shoppingCarts = repository.findByUser(user);
        return shoppingCarts;
    }

    @Override
    public void saveShoppingCart(ShoppingCart shoppingCart) {
        this.repository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartByUserAndItem(User user1, Item item) {
        return this.repository.findByUserAndItem(user1,item);
    }

    @Override
    public void deleteItemOnShoppingCartByUser(User user1, Item item) {
        this.repository.deleteByUserAndItem(user1,item);
    }
}
