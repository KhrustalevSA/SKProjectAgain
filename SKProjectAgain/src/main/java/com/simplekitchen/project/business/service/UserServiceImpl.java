package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.exception.UserNotFoundException;
import com.simplekitchen.project.business.entity.user.api.UserInfo;
import com.simplekitchen.project.business.mapper.user.UserMapper;
import com.simplekitchen.project.business.mapper.user.api.UserMapperImpl;
import com.simplekitchen.project.business.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс сервиса бизнес слоя, реализовывающий интерфейс UserService
 * @see UserService
 * @Author KhrustalevSA
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
     * @return Optional<UserImpl>
     */
    public Optional<UserImpl> save(UserImpl user) {
        log.debug("Save" + user);
        Optional<com.simplekitchen.project.dao.entity.user.UserImpl> savedUser = userService.save(UserMapper.INSTANCE.map(user));
        log.debug("Saved user" + user);
        return savedUser.map(UserMapperImpl.INSTANCE::map);
    }

    /**
     * метод сохранения списка переданных пользователей
     * @param userList
     * @return List<daoUserImpl>
     */
    @Override
    public List<UserImpl> saveAll(UserInfo userInfo) {
        log.debug("save objects:" + userInfo); // --- UserInfoImpl
        if (CollectionUtils.isNotEmpty(userList)) {
            List<com.simplekitchen.project.dao.entity.user.UserImpl> userDaoList = userService.saveAll(
                    userList.stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList())
            );


            List<UserImpl> users = userDaoList.stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList());
            log.debug("");
            return users;
        }

        return Collections.emptyList();
    }

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id
     * @return Optional<UserImpl>
     */
    @Override
    public Optional<UserImpl> get(Long id) {
        log.debug("received id" + id);
        Optional<com.simplekitchen.project.dao.entity.user.UserImpl> userOptional = userService.get(id);
        log.debug("received user" + userOptional.orElseThrow(() -> new UserNotFoundException("user not found", id)));
        log.debug("received user" + userOptional.toString());
        return Optional.of(UserMapper.INSTANCE.DaoToDtoUser(userOptional.get()));
    }

    /**
     * метод получения сущностей всех пользователей
     * @return List<com.simplekitchen.project.dao.entity.user.UserImpl>
     */
    @Override
    public List<UserImpl> getAll() {
        List<com.simplekitchen.project.dao.entity.user.UserImpl> userList = userService.getAll();
        log.debug("received userList" + userList);
        return UserMapper.INSTANCE.DaoToDtoUserList(userList);
    }
//
//    /**
//     * метод получения списка пользователей по списку уникальных идентификаторов
//     * @param ids
//     * @return List<com.simplekitchen.project.dao.entity.user.UserImpl>
//     */
//    @Override
//    public List<UserImpl> getAllById(List<Long> ids) {
//        log.debug("parameter List<long> ids" + ids);
//
//        return ;
//    }
//
//    /**
//     * метод удаления пользователя по уникальному идентификатору
//     * @param id
//     * @return Boolean
//     */
//    @Override
//    public Boolean deleteById(Long id) {
//        return null;
//    }
//
//    /**
//     * метод удаления польователя по его сущности
//     * @param user
//     * @return Boolean
//     */
//    @Override
//    public Boolean delete(com.simplekitchen.project.dao.entity.user.UserImpl user) {
//        return null;
//    }
//
//    /**
//     * метод удаления пользователей по переданному списку пользователей
//     * @param users
//     * @return Boolean
//     */
//    @Override
//    public Boolean deleteAll(List<com.simplekitchen.project.dao.entity.user.UserImpl> users) {
//        return null;
//    }
//
//    /**
//     * метод удаления всех имеющихся пользователей
//     * @return Boolean
//     */
//    @Override
//    public Boolean deleteAll() {
//        return null;
//    }
}
