package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.application.dto.UserLoginDTO;
import com.kreitek.store.application.mapper.UserMapper;
import com.kreitek.store.application.service.service.UserService;
import com.kreitek.store.application.tools.Encryptor;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistence.UserPersistence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserPersistence persistence;
    private final UserMapper mapper;

    public UserServiceImpl(UserPersistence persistence, UserMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public UserDTO saveUser(UserDTO user) {

        User user1 = this.mapper.toEntity(user);
        String password= user1.getPassword();
        String passEncrypted = Encryptor.getMD5(password);
        user1.setPassword(passEncrypted);
        User usersave = this.persistence.saveUser(user1);
        return this.mapper.toDto(usersave);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.persistence.getAllUsers();
        return mapper.toDto(users);
    }

    @Override
    public void deleteUserById(Long userId) {
        this.persistence.deleteUser(userId);
    }

    @Override
    public List<UserDTO> loginVerifier(UserLoginDTO userLoginDTO) {
        String nick = userLoginDTO.getNick();
        List<User> usuarios = this.persistence.getUserByNick(nick);
        User user = usuarios.get(0);
        String passwordLogin = Encryptor.getMD5(userLoginDTO.getPassword());
        System.out.println(passwordLogin);
        if(passwordLogin.equals(user.getPassword())){
            return this.mapper.toDto(usuarios);
        }else{
            List<UserDTO> vacio = new ArrayList<UserDTO>();
            return vacio;
        }
    }

    @Override
    public boolean userExist(String nick) {
        List<User> usuarios = this.persistence.getUserByNick(nick);
        if(usuarios.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
