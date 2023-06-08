package com.kreitek.store.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userSequence")
    private Long id;

    @Column(unique = true,nullable = false,length = 100)
    private String nick;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 100)
    private String lastName;

    @Column(nullable = false,length = 20)
    @Size(min = 9)
    private String phoneNumber;

    @Column(nullable = false,length = 1000,unique = true)
    private String email;

    @Column(nullable = false,length = 1000)
    private String password;

    public Set<Item> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Set<Item> favoritos) {
        this.favoritos = favoritos;
    }

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "usuario_favoritos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> favoritos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ShoppingCart> carritoItems = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> pedidos;

    public List<Order> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Order> pedidos) {
        this.pedidos = pedidos;
    }

    public List<ShoppingCart> getCarritoItems() {
        return carritoItems;
    }

    public void setCarritoItems(List<ShoppingCart> carritoItems) {
        this.carritoItems = carritoItems;
    }

    public User() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
