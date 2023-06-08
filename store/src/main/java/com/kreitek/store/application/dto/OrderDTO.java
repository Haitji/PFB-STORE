package com.kreitek.store.application.dto;

import com.kreitek.store.domain.entity.ShoppingCart;
import com.kreitek.store.domain.entity.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

public class OrderDTO implements Serializable {
    private Long id;

    //private List<ItemShoppingCartDTO> carritos;

    private String orderDate;

    private String shippingAddress;

    private boolean sent;

    public OrderDTO() {
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
