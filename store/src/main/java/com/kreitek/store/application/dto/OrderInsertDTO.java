package com.kreitek.store.application.dto;

import java.io.Serializable;
import java.util.List;

public class OrderInsertDTO implements Serializable {
    private Long id;

    private List<Long> carritos;

    private String orderDate;

    private String shippingAddress;

    public OrderInsertDTO() {
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

    public List<Long> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Long> carritos) {
        this.carritos = carritos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
