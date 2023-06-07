package com.kreitek.store.infrastructure.rest;

import com.kreitek.store.application.Exception.ItemNotFoundException;
import com.kreitek.store.application.Exception.UserNotFoundException;
import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.ItemShoppingCartDTO;
import com.kreitek.store.application.dto.ShoppingCartInsertDTO;
import com.kreitek.store.application.service.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ShoppingCartController {

    private final ShoppingCartService service;

    @Autowired
    public ShoppingCartController(ShoppingCartService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping(value = "/users/{userNick}/shoppingcart")
    public ResponseEntity<List<ItemShoppingCartDTO>> getShoppingCartByUser(@PathVariable String userNick){
        try{
        List<ItemShoppingCartDTO> items = this.service.getShoppingCartByUser(userNick);
        return new ResponseEntity<>(items, HttpStatus.OK);
        }catch (UserNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/users/{userNick}/shoppingcart",consumes = "application/json")
    public ResponseEntity<Void> addItemsOnShoppingCart(@PathVariable String userNick, @RequestBody ShoppingCartInsertDTO shop){
        try{
            this.service.addItemsOnShoppingCart(userNick,shop.getId(),shop.getUnits());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(ItemNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{userNick}/shoppingcart/{itemId}")
    public ResponseEntity<Void> deleteItemOnShoppingCart(@PathVariable String userNick,@PathVariable Long itemId){
        try{
        this.service.deleteItemOnShoppingCart(userNick,itemId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(ItemNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
