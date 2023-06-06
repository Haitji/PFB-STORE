package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.application.dto.UserLoginDTO;
import com.kreitek.store.application.mapper.ItemMapper;
import com.kreitek.store.application.mapper.UserMapper;
import com.kreitek.store.application.service.service.UserService;
import com.kreitek.store.application.tools.Encryptor;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistence.ItemPersistence;
import com.kreitek.store.domain.persistence.UserPersistence;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserPersistence persistence;
    private final UserMapper mapper;

    private final ItemPersistence itemPersistence;
    private final ItemMapper itemMapper;

    public UserServiceImpl(UserPersistence persistence, UserMapper mapper,ItemPersistence itemPersistence,ItemMapper itemMapper) {
        this.persistence = persistence;
        this.mapper = mapper;
        this.itemPersistence = itemPersistence;
        this.itemMapper = itemMapper;
    }

    @Override
    @Transactional
    public UserDTO saveUser(UserDTO user) {

        User user1 = this.mapper.toEntity(user);
        String password= user1.getPassword();
        String passEncrypted = Encryptor.getMD5(password);
        user1.setPassword(passEncrypted);
        User usersave = this.persistence.saveUser(user1);
        return this.mapper.toDto(usersave);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> users = this.persistence.getAllUsers();
        return mapper.toDto(users);
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        this.persistence.deleteUser(userId);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public boolean userExist(String nick) {
        List<User> usuarios = this.persistence.getUserByNick(nick);
        if(usuarios.isEmpty()){
            return false;
        }else{
            return true;
        }
    }



    @Override
    @Transactional(readOnly = true)
    public List<Long> getFavoriteIdByUserNick(String userNick) {
        List<UserDTO> userList = mapper.toDto(persistence.getUserByNick(userNick));
        UserDTO user;
        if(!userList.isEmpty()){
            user=userList.get(0);
            return user.getFavoritos().stream()
                    .map(ItemDTO::getId)
                    .collect(Collectors.toList());
        }else{
            List<Long> vacio = new ArrayList<Long>();
            return vacio;
        }
    }

    @Override
    @Transactional
    public boolean addUserFavoriteItemById(String userNick, Long itemId) {
        List<UserDTO> userDTOS = this.mapper.toDto(persistence.getUserByNick(userNick));
        UserDTO user;
        if(!userDTOS.isEmpty()){
            user=userDTOS.get(0);
            Optional<Item> items = itemPersistence.getItemById(itemId);
            if(items.isPresent()){
                Item item = items.get();
                user.getFavoritos().add(itemMapper.toDto(item));
                persistence.saveUser(mapper.toEntity(user));
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteFavoriteItemById(String userNick, Long itemId) {
        List<User> user = persistence.getUserByNick(userNick);
        User user1;
        if(!user.isEmpty()){
            user1=user.get(0);
            Optional<Item> items = itemPersistence.getItemById(itemId);
            if(items.isPresent()){
                Item item = items.get();
                user1.getFavoritos().remove(item);
                persistence.saveUser(user1);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    @Transactional
    public Set<ItemDTO> getItemFavoriteByUserNick(String userNick) {
        List<User> user = persistence.getUserByNick(userNick);
        UserDTO userDTO;
        if(!user.isEmpty()){
            userDTO = mapper.toDto(user.get(0));
            return userDTO.getFavoritos();
        }else{
            Set<ItemDTO> vacio = new HashSet<>();
            return vacio;
        }
    }

}
