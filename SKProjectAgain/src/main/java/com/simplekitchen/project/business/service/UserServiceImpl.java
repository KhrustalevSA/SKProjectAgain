package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.business.entity.user.UserListImpl;
import com.simplekitchen.project.business.entity.user.UserResponseInfoImpl;
import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import com.simplekitchen.project.business.entity.user.api.UserList;
import com.simplekitchen.project.business.entity.user.api.UserResponseInfo;
import com.simplekitchen.project.business.exception.UserRequestInfoNotFoundException;
import com.simplekitchen.project.business.exception.UserResponseInfoNotFoundException;
import com.simplekitchen.project.business.mapper.user.UserMapper;
import com.simplekitchen.project.business.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс сервиса бизнес слоя, реализовывающий интерфейс UserService
 * @see UserService
 * @author KhrustalevSA
 * @since 29.01.2023
 * */
@Slf4j
@Service(value = "UserServiceImplBusiness")
public class UserServiceImpl implements UserService {

    /**
     * поле сервиса пользователя
     */
    private static com.simplekitchen.project.dao.service.api.UserService userService;

    /**
     * конструктор класса с параметром сервиса
     * @param userServiceDao
     */
    @Autowired
    public UserServiceImpl(com.simplekitchen.project.dao.service.api.UserService userServiceDao) {
        this.userService = userServiceDao;
    }

    /**
     * метод сохранения пользователя с возвращением сохраненного пользователя
     * @param user
     * @return Optional<UserImpl> сохраненный пользователь
     */
    public Optional<UserImpl> save(UserImpl user) {
        log.debug("Save" + user);
        Optional<com.simplekitchen.project.dao.entity.user.UserImpl> savedUser = userService
                .save(UserMapper.INSTANCE.map(user));
        log.debug("Saved user" + user);
        return savedUser.map(UserMapper.INSTANCE::map);
    }

    /**
     * метод сохранения списка переданных пользователей
     * @param userList
     * @return UserListImpl
     */
    @Override
    public UserListImpl saveAll(UserListImpl userList) {
        log.debug("save objects:" + userList); // работает или нет?
        if (CollectionUtils.isNotEmpty(userList.getUserList())) {
            List<com.simplekitchen.project.dao.entity.user.UserImpl> userDaoList = userService.saveAll(
                    userList.getUserList().stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList())
            );

            return UserListImpl.builder()
                    .userList(userDaoList.stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList()))
                    .build();
        }

        return UserListImpl.builder().build();
    }

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id
     * @return Optional<UserImpl>
     */
    @Override
    public Optional<UserImpl> get(Long id) {
        log.debug("received id" + id);
        Optional<com.simplekitchen.project.dao.entity.user.UserImpl> foundUser = userService.get(id);
        log.debug("found user = " + foundUser);
        return foundUser.map(UserMapper.INSTANCE::map);
    }

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param userInfo
     * @return Optional<UserImpl>
     */
    @Override
    public UserResponseInfo get(UserRequestInfo userInfo) throws UserRequestInfoNotFoundException {
        log.debug("requested userInfo" + userInfo);
        Optional<List<com.simplekitchen.project.dao.entity.user.UserImpl>> usersOptional = userService.get(userInfo);
        log.debug("received user" + usersOptional.orElseThrow(() ->
                new UserRequestInfoNotFoundException("user not found", userInfo)));
        return UserResponseInfoImpl.builder()
                .status(StatusImpl.builder().success(true).build())
                .userList(usersOptional.get()
                        .stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList()))
                .build();
    }

    /**
     * метод получения сущностей всех пользователей
     * @return List<com.simplekitchen.project.dao.entity.user.UserImpl>
     */
    @Override
    public Optional<List<UserImpl>> getAll() throws UserResponseInfoNotFoundException {
        if (userService.getAll().isPresent()) {
            Optional<List<UserImpl>> userListOptional = Optional.of(userService.getAll().get()
                    .stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList()));
            log.debug("received userList" + userListOptional);
            return userListOptional;
        }

        return Optional.empty();
    }

//    /**
//     * метод получения списка пользователей по списку уникальных идентификаторов
//     * @param ids
//     * @return List<com.simplekitchen.project.dao.entity.user.UserImpl>
//     */
//    @Override
//    public UserList getAllById(List<Long> ids) {
//        log.debug("parameter List<long> ids" + ids);
//
//        return ;
//    }

    /**
     * метод удаления пользователя по уникальному идентификатору
     * @param id
     * @return Boolean
     */
    @Override
    public Boolean deleteById(Long id) {
        log.debug("received id: " + id);
        Optional<com.simplekitchen.project.dao.entity.user.UserImpl> userToDelete = userService.get(id);
        log.debug("found user: " + userToDelete);

        if (userToDelete.isPresent()) {
            userService.deleteById(id);
            return true;
        }

        return false;
    }

//    /**
//     * метод удаления польователя по пришедшей информации
//     * @param user
//     * @return Boolean
//     */
//    @Override
//    public Boolean delete(UserRequestInfo userRequestInfo) {
//        userService.delete()
//    }

}
