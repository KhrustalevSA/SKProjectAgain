package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.business.entity.user.UserListImpl;
import com.simplekitchen.project.business.entity.user.UserRequestInfoImpl;
import com.simplekitchen.project.business.entity.user.UserResponseInfoImpl;
import com.simplekitchen.project.business.entity.user.api.UserList;
import com.simplekitchen.project.business.entity.user.api.UserResponseInfo;
import com.simplekitchen.project.business.exception.UserResponseInfoNotFoundException;
import com.simplekitchen.project.business.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * РЕСТ контроллер для работы с пользователями
 */
@Slf4j
@RestController
public class UserController {
//mapstract Бины
// response request переделать, отказ от листов,

// orElseThrow в сервисе так или нет?
// метод с испльзованием класса информации о пользователе как правильно?
// метод get в сервисе дао?
// dao service должен ли знать о бизнес слое?
// должен ли знать сервис о requestInfo

// Переделать get методы сервисов
// Валидатор
// Exception классы
// UserController переделать до низа. Контроллер всегда отвечает, все делает сервис он не кидает исключений,
//          все ошибки в плохой ответ, в бд сервисе только исключения БД
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

    /**
     * Метод сохранения списка переданных пользователей
     * @param userList
     * @return саисок сохраненных пользователей
     */
    @PostMapping("/save/all")
    public ResponseEntity<UserList> saveAll(@RequestBody UserListImpl userList) {
        if (userList.getUserList() == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userService.saveAll(userList), HttpStatus.OK);
    }

    /**
     * метод получения пользователя по имеющейся информации
     * @param userRequestInfo
     * @return информация о пользователе
     */
    @GetMapping("/get")
    public UserResponseInfo get(@RequestBody UserRequestInfoImpl userRequestInfo) {

        try {

            return userService.get(userRequestInfo);
        } catch (Throwable e) {
            log.error("Ошибка при получении пользователя.", e);
            return UserResponseInfoImpl.builder()
                    .status(StatusImpl.builder()
                            .success(false)
                            .description(e.getMessage())
                            .build())
                    .build();
        }

    }

    /**
     * Метод получения всех имеющихся пользователей
     * @return информация о пользователях
     * @throws UserResponseInfoNotFoundException
     */
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

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Boolean deleteById(@RequestParam Long id) {
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
