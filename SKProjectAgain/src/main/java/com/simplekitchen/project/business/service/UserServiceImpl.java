package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.entity.user.UserListImpl;
import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import com.simplekitchen.project.business.entity.user.api.UserList;
import com.simplekitchen.project.business.exception.UserRequestInfoNotFoundException;
import com.simplekitchen.project.business.mapper.user.UserMapper;
import com.simplekitchen.project.business.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
     * @return List<daoUserImpl>
     */
    @Override
    public UserList saveAll(UserList userList) {
        log.debug("save objects:" + userList); // работает или нет?
        if (CollectionUtils.isNotEmpty(userList.getUserList())) {
            List<com.simplekitchen.project.dao.entity.user.UserImpl> userDaoList = userService.saveAll(
                    userList.getUserList().stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList())
            );

            UserList savedUsers = new UserListImpl(userDaoList.stream().map(UserMapper.INSTANCE::map)
                    .collect(Collectors.toList()));
            log.debug("");
            return savedUsers;
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
    public Optional<UserImpl> get(UserRequestInfo userInfo) throws UserRequestInfoNotFoundException {
        log.debug("requested userInfo" + userInfo);
        Optional<com.simplekitchen.project.dao.entity.user.UserImpl> userOptional = userService.get(userInfo);
        log.debug("received user" + userOptional.orElseThrow(() ->
                new UserRequestInfoNotFoundException("user not found", userInfo)));
        log.debug("received user" + userOptional);
        return Optional.of(UserMapper.INSTANCE.map(userOptional.get()));
    }

    /**
     * метод получения сущностей всех пользователей
     * @return List<com.simplekitchen.project.dao.entity.user.UserImpl>
     */
    @Override
    public UserList getAll() {
        UserList userListClassVar = UserListImpl.builder()
                .userList(userService.getAll().stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList()))
                .build();
        log.debug("received userList" + userListClassVar);
        return userListClassVar;
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

    /**
     * метод удаления пользователей по переданному списку пользователей
     * @param userList
     * @return Boolean
     */
    @Override
    public Boolean deleteAll(UserList userList) {
        log.debug("received user list = " + userList);
        List<UserImpl> allFoundUsers = userService.getAll().stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList());
        log.debug("found users: " + allFoundUsers);
        if (!allFoundUsers.isEmpty()) {
            userService.deleteAll(userList.getUserList().stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList()));
            return true;
        }
        return false;
    }

    /**
     * метод удаления всех имеющихся пользователей
     * @return Boolean
     */
    @Override
    public Boolean deleteAll() {
        userService.deleteAll();
        return true;
    }
}
