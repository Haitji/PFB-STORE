package com.kreitek.store.application.Exception;

public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException(String mensaje) {
        super(mensaje);
    }
}
