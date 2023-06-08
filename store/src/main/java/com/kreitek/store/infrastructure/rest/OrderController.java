package com.kreitek.store.infrastructure.rest;

import com.kreitek.store.application.Exception.OrderNotFoundException;
import com.kreitek.store.application.Exception.UserNotFoundException;
import com.kreitek.store.application.dto.ItemShoppingCartDTO;
import com.kreitek.store.application.dto.OrderDTO;
import com.kreitek.store.application.dto.OrderInsertDTO;
import com.kreitek.store.application.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin
    @GetMapping(value = "/users/{userNick}/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrdersByUser(@PathVariable String userNick){
        try{
            List<OrderDTO> orders = this.orderService.getAllOrdersByUser(userNick);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (UserNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/users/{userNick}/orders/{orderId}")
    public ResponseEntity<List<ItemShoppingCartDTO>> getAllOrdersByUser(@PathVariable String userNick,@PathVariable Long orderId){
        try{
            List<ItemShoppingCartDTO> orders = this.orderService.getAllOrdersByUserAndOrderId(userNick,orderId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (UserNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (OrderNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/users/{userNick}/orders")
    public ResponseEntity<Void> addOrdersByUser(@PathVariable String userNick,@RequestBody OrderInsertDTO orderInsertDTO){
        this.orderService.addOrderByUser(userNick,orderInsertDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/orders/{orderId}")
    public ResponseEntity<Void> deleteOrdersById(@PathVariable Long orderId){
        this.orderService.deleteOrdersById(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value="/users/orders/{orderId}/shoppingCart/{shoppingCartId}")
    public ResponseEntity<Void> removeShoppingCartFromOrder(@PathVariable Long orderId,@PathVariable Long shoppingCartId){
        this.orderService.removeShoppingCartFromOrder(orderId,shoppingCartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
