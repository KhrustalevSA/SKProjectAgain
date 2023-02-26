package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.entity.common.LongListImpl;
import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.business.entity.user.UserRequestInfoImpl;
import com.simplekitchen.project.business.entity.user.UserResponseInfoImpl;
import com.simplekitchen.project.business.entity.user.api.UserResponseInfo;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.service.api.UserControllerService;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.UserImplListImpl;
import com.simplekitchen.project.dto.entity.user.api.User;
import com.simplekitchen.project.dto.entity.user.api.UserList;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * РЕСТ контроллер для работы с пользователями
 * @author KhrustalevSA
 * @since 26.02.2023
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
//mapstract Бины
// UserController переделать до низа. Контроллер всегда отвечает, все делает сервис он не кидает исключений,
// все ошибки в плохой ответ, в бд сервисе только исключения БД
// saveAll методы + UserList как сделать правильно?

    /**
     * сервис работы с пользователями
     */
    private final UserControllerService userControllerService;

    /**
     * объект некорректного веб ответа
     */
    private static final UserResponseInfoImpl INVALID_DATA = UserResponseInfoImpl.builder()
            .status(StatusImpl.builder().success(false).description("Некорректно введенные данные").build())
            .build();

    /**
     * конструктор с автоопределением бина
     * @param service сервис пользователей
     */
    @Autowired
    public UserController(UserControllerService service) {
        this.userControllerService = service;
    }

    /**
     * метод сохранения пользователя
     * @param user объект сохраняемого пользователя
     * @return информация о сохраненном пользователе
     */
    @PostMapping("/save")
    public UserResponseInfo save(@RequestBody UserImpl user) throws BaseException, DataBaseException {
        if (validate(user)) {
            User savedUser = userControllerService.save(user);
            if (savedUser.getId() == null) {
                return UserResponseInfoImpl.builder()
                        .status(StatusImpl.builder()
                                .success(false)
                                .description("Ошибка сохранения пользователя")
                                .build())
                        .build();
            }
            return UserResponseInfoImpl.builder()
                    .userList(Lists.newArrayList(savedUser))
                    .status(StatusImpl.builder().success(true).build())
                    .build();
        }
        return INVALID_DATA;
    }

    /**
     * Метод сохранения списка переданных пользователей
     * @param userList список пользователей
     * @return информация содержащая список сохраненных пользователей
     */
    @PostMapping("/save/all")
    public UserResponseInfo saveAll(@RequestBody UserImplListImpl userList) throws BaseException, DataBaseException {
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
     * метод получения пользователей по имеющейся информации
     * @param userRequestInfo информация для получения пользователей
     * @return информация о найденных пользователях
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
     * @return класс информации со списком всех имеющихся пользователей
     * @throws BaseException общий класс ошибки обработки исключения приложения
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

    /**
     * метод удаления пользователя по уникальному идентификатору
     * @param id идентификатор пользователя
     * @return логический ответ
     */
    @PostMapping("/deleteById")
    public Boolean deleteById(@RequestParam Long id) throws BaseException {
        if (validate(id)){
            return userControllerService.deleteById(id);
        }
        return false;
    }

    /**
     * метод удаления пользователей по списку идентификаторов
     * @param longList список идентификаторов пользователей
     * @return логический ответ
     * @throws BaseException общий класс ошибки обработки исключения приложения
     */
    @PostMapping("/deleteByIdList")
    public Boolean deleteByIdList(@RequestBody LongListImpl longList) throws BaseException {
        if (validate(longList)) {
            Boolean deleteCheck = true;
            //longList.getLongList().stream().map(userControllerService::deleteById).collect(Collectors.toList());
            for (Long id : longList.getLongList()) {
                deleteCheck = userControllerService.deleteById(id);
                if (!deleteCheck) {
                    return false;
                }
            }
            return deleteCheck;
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

    /**
     * метод проверки объекта на null
     * @param o объект проверки
     * @return логический ответ
     */
    private Boolean validate(Object o){
        return o != null;
    }

}
