package com.kreitek.store.application.Exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String mensaje) {
        super(mensaje);
    }
}
