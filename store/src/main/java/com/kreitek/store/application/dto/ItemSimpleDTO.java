package com.kreitek.store.application.dto;

import java.io.Serializable;

public class ItemSimpleDTO implements Serializable{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemSimpleDTO() {
    }
}
