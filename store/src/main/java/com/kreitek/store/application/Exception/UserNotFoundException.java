package com.kreitek.store.application.Exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String mensaje) {
        super(mensaje);
    }
}
