package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.business.entity.user.UserListImpl;
import com.simplekitchen.project.business.entity.user.UserRequestInfoImpl;
import com.simplekitchen.project.business.entity.user.UserResponseInfoImpl;
import com.simplekitchen.project.business.entity.user.api.UserList;
import com.simplekitchen.project.business.entity.user.api.UserResponseInfo;
import com.simplekitchen.project.business.exception.UserRequestInfoNotFoundException;
import com.simplekitchen.project.business.exception.UserResponseInfoNotFoundException;
import com.simplekitchen.project.business.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * РЕСТ контроллер для работы с пользователями
 */
@RestController
public class UserController {
//mapstract Бины
// response request переделать, отказ от листов,

// orElseThrow в сервисе так или нет?
// метод с испльзованием класса информации о пользователе как правильно?
// метод get в сервисе дао?
// dao service должен ли знать о бизнес слое?
// должен ли знать сервис о requestInfo

    private final UserService userService;

    /**
     * конструктор с автоопределением бина
     * @param service
     */
    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    /**
     * метод сохранения пользователя
     * @param user
     * @return ResponseEntity<UserImpl>
     */
    @PostMapping("/save")
    public ResponseEntity<UserImpl> save(@RequestBody UserImpl user) {
        return userService.save(user).map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping("/save/all")
    public ResponseEntity<UserList> saveAll(@RequestBody UserListImpl userList) {
        if (userList.getUserList() == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userService.saveAll(userList), HttpStatus.OK);
    }

    @GetMapping("/get")
    public UserResponseInfo get(@RequestBody UserRequestInfoImpl userRequestInfo) throws UserRequestInfoNotFoundException {
        if (userRequestInfo == null) {
            return UserResponseInfoImpl.builder()
                    .status(StatusImpl.builder()

                            .success(false)
                            .description("UserResponse are null")
                            .build())
                    .build();
        }

        ///////////////////////////////////////////////
        //Сейчас работает
        ////
        List<UserImpl> listUser = new ArrayList<>();
        listUser = userService.get(userRequestInfo).getUserList();
        //listUser.add(userService.get(userRequestInfo).orElseThrow(() -> new UserRequestInfoNotFoundException("user not found", userRequestInfo)));
        ///////////////////////////////////////////////
        //
        //Было но не работает
        ///////////////////////////////////////////////
        //UserListImpl userList = new UserListImpl();
        //userList.add(userService.get(userRequestInfo));
        ///////////////////////////////////////////////

        return UserResponseInfoImpl.builder()
                .userList(userService.get(userRequestInfo).getUserList())
                .status(StatusImpl.builder().success(true).build()).build();

    }

    @GetMapping("/get/all")
    public UserResponseInfo getAll() throws UserResponseInfoNotFoundException {
        if (userService.getAll().isPresent()) {

            return UserResponseInfoImpl.builder()
                    .userList(userService.getAll().get()).build();
        }
        return UserResponseInfoImpl.builder().status(StatusImpl.builder()
                .success(false).description("users not found").build()).build();
    }

//    @GetMapping("/get/all/ids")
//    public ResponseEntity<List<UserImpl>> getAllById(@RequestBody List<Long> ids) {
//        return new ResponseEntity<>(userService.getAllById(ids), HttpStatus.OK);
//    }

    @PostMapping("/deleteById")
    public Boolean deleteById(@RequestBody Long id) {
        userService.deleteById(id);
        return Boolean.TRUE;
    }

    @PostMapping("/deleteByIdList")
    public UserResponseInfo deleteById(@RequestBody UserListImpl userList) {
        if (!userList.getUserList().isEmpty()) {
            List<UserImpl> userListWithId = userList.getUserList();
            for (UserImpl user : userListWithId) {
                userService.deleteById(user.getId());
            }
            return UserResponseInfoImpl.builder().status(StatusImpl.builder().success(true).build()).build();
        }
        return UserResponseInfoImpl.builder().status(StatusImpl.builder()
                .success(false).description("users not found").build()).build();

    }
    
    @PostMapping("/showUserEntity")
    public UserImpl showUserEntity(){
        return UserImpl.builder().id(1L).name("Ivan").surname("").patronymic("").build();
    }

    @PostMapping("/showUserListEntity")
    public List<UserImpl> showUserListEntity(){
        List<UserImpl> userList = new ArrayList<>();
        userList.add(UserImpl.builder().id(1L).name("Ivan").surname("").patronymic("").build());
        userList.add(UserImpl.builder().id(2L).name("Petr").surname("").patronymic("").build());
        return userList;
    }

    @PostMapping("/showGetAllById")
    public List<Long> showGetAllById(){
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        return list;
    }

}
