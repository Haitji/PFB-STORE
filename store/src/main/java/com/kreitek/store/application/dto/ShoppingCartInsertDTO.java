package com.kreitek.store.application.dto;

import java.io.Serializable;

public class ShoppingCartInsertDTO implements Serializable {
    private Long id;
    private int units;

    public ShoppingCartInsertDTO() {
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
