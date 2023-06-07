package com.kreitek.store.application.Exception;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String mensaje) {
        super(mensaje);
    }
}
