package com.kreitek.store.domain.persistence;

import com.kreitek.store.domain.entity.User;

import java.util.List;

public interface UserPersistence {
    User saveUser(User toEntity);

    List<User> getAllUsers();

    void deleteUser(Long userId);

    List<User> getUserByNick(String nick);
}
