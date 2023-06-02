package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserPersistenceImpl implements UserPersistence {

    private final UserRepository userRepository;

    @Autowired
    public UserPersistenceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User toEntity) {
        return this.userRepository.save(toEntity);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUserByNick(String nick) {
        return this.userRepository.findByNick(nick);
    }
}
