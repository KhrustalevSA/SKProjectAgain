package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.business.entity.user.UserListImpl;
import com.simplekitchen.project.business.entity.user.UserResponseInfoImpl;
import com.simplekitchen.project.business.entity.user.api.UserList;
import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import com.simplekitchen.project.business.entity.user.api.UserResponseInfo;
import com.simplekitchen.project.business.exception.UserRequestInfoNotFoundException;
import com.simplekitchen.project.business.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<UserList> saveAll(@RequestBody UserList userList) {
        if (userList.getUserList().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userService.saveAll(userList), HttpStatus.OK);
    }

    @GetMapping("/get")
    public UserResponseInfo get(@RequestParam UserRequestInfo userRequestInfo) throws UserRequestInfoNotFoundException {
        if (userRequestInfo == null) {
            return UserResponseInfoImpl.builder()
                    .status(StatusImpl.builder()
                                        .success(false).description("UserResponse are null")
                                        .build())
                    .build();
        }

        ///////////////////////////////////////////////
        UserList userList = new UserListImpl();
        userList.add(userService.get(userRequestInfo));
        ///////////////////////////////////////////////

        return UserResponseInfoImpl.builder()
                .userList(userList).status(StatusImpl.builder().success(true).build()).build();

    }

    @GetMapping("/get/all")
    public UserResponseInfo getAll() {
        UserList userList = userService.getAll();

        if (userList == null)
        return UserResponseInfoImpl.builder().status(StatusImpl.builder()
                .success(false).description("users not found").build()).build();

        return UserResponseInfoImpl.builder()
                .userList(userList).status(StatusImpl.builder().success(true).build()).build();
    }

//    @GetMapping("/get/all/ids")
//    public ResponseEntity<List<UserImpl>> getAllById(@RequestBody List<Long> ids) {
//        return new ResponseEntity<>(userService.getAllById(ids), HttpStatus.OK);
//    }

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
