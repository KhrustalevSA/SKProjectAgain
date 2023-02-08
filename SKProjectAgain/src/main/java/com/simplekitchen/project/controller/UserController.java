package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.business.entity.common.api.LongList;
import com.simplekitchen.project.business.entity.user.UserListImpl;
import com.simplekitchen.project.business.entity.user.UserRequestInfoImpl;
import com.simplekitchen.project.business.entity.user.UserResponseInfoImpl;
import com.simplekitchen.project.business.entity.user.api.UserList;
import com.simplekitchen.project.business.entity.user.api.UserResponseInfo;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.service.api.UserControllerService;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
// все ошибки в плохой ответ, в бд сервисе только исключения БД
    private final UserControllerService userControllerService;
    private static final UserResponseInfoImpl INVALID_DATA = UserResponseInfoImpl.builder()
            .status(StatusImpl.builder().success(false).description("Некорректно введенные данные").build())
            .build();

    /**
     * конструктор с автоопределением бина
     * @param service
     */
    @Autowired
    public UserController(UserControllerService service) {
        this.userControllerService = service;
    }

    /**
     * метод сохранения пользователя
     * @param user
     * @return ResponseEntity<UserEntityImpl>
     */
    @PostMapping("/save")
    public UserResponseInfo save(@RequestBody UserImpl user) throws BaseException, DataBaseException {
        if (validate(user)) {
            UserList savedUser = (UserList) userControllerService.save(user);
            if (savedUser.getUserList() != null) {
                return UserResponseInfoImpl.builder()
                        .userList(savedUser.getUserList())
                        .status(StatusImpl.builder().success(true).build())
                        .build();
            }
        }
        return INVALID_DATA;

    }

    /**
     * Метод сохранения списка переданных пользователей
     * @param userList
     * @return саисок сохраненных пользователей
     */
    @PostMapping("/save/all")
    public UserResponseInfo saveAll(@RequestBody UserListImpl userList) throws BaseException, DataBaseException {
        if (validate(userList)) {
            UserList savedUser = userControllerService.saveAll(userList);
            if (savedUser.getUserList() != null) {
                return UserResponseInfoImpl.builder()
                        .userList(savedUser.getUserList())
                        .status(StatusImpl.builder().success(true).build())
                        .build();
            }
        }
        return INVALID_DATA;
    }

    /**
     * метод получения пользователя по имеющейся информации
     * @param userRequestInfo
     * @return информация о пользователе
     */
    @GetMapping("/get")
    public UserResponseInfo get(@RequestBody UserRequestInfoImpl userRequestInfo) throws BaseException {
       if (validate(userRequestInfo)) {
           UserList userList = userControllerService.get(userRequestInfo);
           if (userList.getUserList() != null) {
               return UserResponseInfoImpl.builder()
                       .userList(userList.getUserList())
                       .status(StatusImpl.builder().success(true).build())
                       .build();
           }
       }
        return INVALID_DATA;
    }

    /**
     * Метод получения всех имеющихся пользователей
     * @return информация о пользователях
     * @throws BaseException
     */
    @GetMapping("/get/all")
    public UserResponseInfo getAll() throws BaseException {
        UserList allUsers = userControllerService.getAll();
        if (allUsers.getUserList() != null) {
            return UserResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(true).build())
                    .userList(allUsers.getUserList())
                    .build();
        }
        return INVALID_DATA;
    }

//    @GetMapping("/get/all/ids")
//    public ResponseEntity<List<UserEntityImpl>> getAllById(@RequestBody List<Long> ids) {
//        return new ResponseEntity<>(userControllerService.getAllById(ids), HttpStatus.OK);
//    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Boolean deleteById(@RequestParam Long id) throws BaseException {
        if (validate(id)){
            return userControllerService.deleteById(id);
        }
        return false;
    }


    @PostMapping("/deleteByIdList")
    public Boolean deleteByIdList(@RequestBody LongList longList) throws BaseException {
        if (validate(longList)) {
            //longList.getLongList().stream().map(userControllerService::deleteById).collect(Collectors.toList());
            for (Long id : longList.getLongList()) {
                userControllerService.deleteById(id);
            }
            return true;
        }
        return false;

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

    private Boolean validate(Object o){
        return o != null;
    }

}
