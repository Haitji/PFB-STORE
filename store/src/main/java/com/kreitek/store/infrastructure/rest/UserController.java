package com.kreitek.store.infrastructure.rest;

import com.kreitek.store.application.dto.CategoryDTO;
import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.application.dto.UserLoginDTO;
import com.kreitek.store.application.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping(value = "/user",produces = "application/json",consumes = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        boolean exist = this.userService.userExist(userDTO.getNick());
        if(!exist){
            userDTO = this.userService.saveUser(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @CrossOrigin
    @PostMapping(value = "/users/login",produces = "application/json",consumes = "application/json")
    public ResponseEntity<UserDTO> userLogin(@RequestBody UserLoginDTO userLoginDTO){
        boolean exist = this.userService.userExist(userLoginDTO.getNick());
        if(exist){
            List<UserDTO> login = this.userService.loginVerifier(userLoginDTO);
            if(login.isEmpty()){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }else{
                return new ResponseEntity<>(HttpStatus.OK);
                //return new ResponseEntity<>(login.get(0),HttpStatus.OK); En el caso de que quieres devolver tambien los datos del usuario
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/users/{userNick}/favoritos-id")
    public ResponseEntity<List<Long>> getIdFavoriteOfUser(@PathVariable String userNick){
        List<Long> idFavorite = this.userService.getFavoriteIdByUserNick(userNick);
        return new ResponseEntity<>(idFavorite,HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/users/{userNick}/favoritos")
    public ResponseEntity<Set<ItemDTO>> getItemFavoriteOfUser(@PathVariable String userNick){
        Set<ItemDTO> itemDTOS = this.userService.getItemFavoriteByUserNick(userNick);
        return new ResponseEntity<>(itemDTOS,HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/users/{userNick}/favoritos/{itemId}")
    public ResponseEntity<Void> getIdFavoriteOfUser(@PathVariable String userNick,@PathVariable Long itemId){
        boolean correct=userService.addUserFavoriteItemById(userNick,itemId);
        if(correct){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{userNick}/favoritos/remove/{itemId}")
    public ResponseEntity<Void> deleteFavoriteOfUser(@PathVariable String userNick,@PathVariable Long itemId){
        boolean correct=userService.deleteFavoriteItemById(userNick,itemId);
        if(correct){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/users",produces = "application/json")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOS = this.userService.getAllUsers();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        this.userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
