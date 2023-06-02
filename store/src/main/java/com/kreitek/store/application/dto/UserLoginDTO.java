package com.kreitek.store.application.dto;

import java.io.Serializable;

public class UserLoginDTO implements Serializable {

    private String nick;

    private String password;

    public UserLoginDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
