package com.kreitek.store.application.service.service;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.application.dto.UserLoginDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO user);

    List<UserDTO> getAllUsers();

    void deleteUserById(Long userId);

    List<UserDTO> loginVerifier(UserLoginDTO userLoginDTO);

    boolean userExist(String nick);
}
