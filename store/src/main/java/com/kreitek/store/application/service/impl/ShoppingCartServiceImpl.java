package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.Exception.ItemNotFoundException;
import com.kreitek.store.application.Exception.UserNotFoundException;
import com.kreitek.store.application.dto.ItemShoppingCartDTO;
import com.kreitek.store.application.mapper.ShoppingCartMapper;
import com.kreitek.store.application.service.service.ShoppingCartService;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.entity.ShoppingCart;
import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistence.ItemPersistence;
import com.kreitek.store.domain.persistence.ShoppinCartPersistence;
import com.kreitek.store.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppinCartPersistence persistence;
    private final ShoppingCartMapper mapper;
    private final UserPersistence userPersistence;
    private final ItemPersistence itemPersistence;

    @Autowired
    public ShoppingCartServiceImpl(ShoppinCartPersistence persistence, ShoppingCartMapper mapper, UserPersistence userPersistence, ItemPersistence itemPersistence) {
        this.persistence = persistence;
        this.mapper = mapper;
        this.userPersistence = userPersistence;
        this.itemPersistence = itemPersistence;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemShoppingCartDTO> getShoppingCartByUser(String userNick) {
        List<User> user = userPersistence.getUserByNick(userNick);
        if(!user.isEmpty()){
            User user1 = user.get(0);
            List<ShoppingCart> shoppingCarts = persistence.GetShoppingCartByUser(user1);
            return shoppingCarts.stream().map(this.mapper::shoppingCartToItemCartDTO).collect(Collectors.toList());
        }else{
            throw new UserNotFoundException("Usuario no econtrado"+userNick);
        }
    }

    @Override
    @Transactional
    public void addItemsOnShoppingCart(String userNick, Long id, int units) {
        List<User> user = userPersistence.getUserByNick(userNick);
        if(!user.isEmpty()) {
            User user1 = user.get(0);
            Optional<Item> item = itemPersistence.getItemById(id);
            if(item.isPresent()){
                Optional<ShoppingCart> verifier = persistence.getShoppingCartByUserAndItem(user1,item.get());
                if(verifier.isPresent()){
                    ShoppingCart shoppingCart=verifier.get();
                    shoppingCart.setUnits(units);
                    persistence.saveShoppingCart(shoppingCart);
                }else{
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser(user1);
                    shoppingCart.setItem(item.get());
                    shoppingCart.setUnits(units);
                    persistence.saveShoppingCart(shoppingCart);
                }
            }else{
                throw new ItemNotFoundException("Item no encontrado");
            }
        }else{
            throw new UserNotFoundException("Usuario no econtrado"+userNick);
        }
    }

    @Override
    @Transactional
    public void deleteItemOnShoppingCart(String userNick, Long itemId) {
        List<User> user = userPersistence.getUserByNick(userNick);
        if(!user.isEmpty()) {
            User user1 = user.get(0);
            Optional<Item> item = itemPersistence.getItemById(itemId);
            if(item.isPresent()){
                this.persistence.deleteItemOnShoppingCartByUser(user1,item.get());
            }else{
                throw new ItemNotFoundException("Item no encontrado");
            }
        }else{
            throw new UserNotFoundException("Usuario no econtrado"+userNick);
        }
    }
}
