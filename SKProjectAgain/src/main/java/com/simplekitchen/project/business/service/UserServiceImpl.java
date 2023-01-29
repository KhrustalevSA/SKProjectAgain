package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.mapper.user.api.DaoToDtoUserMapper;
import com.simplekitchen.project.business.mapper.user.api.DaoToDtoUserMapperImpl;
import com.simplekitchen.project.business.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс реализовывающий интерфейс UserService
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
        Optional<com.simplekitchen.project.dao.entity.user.UserImpl> savedUser = userService.save(DaoToDtoUserMapper.INSTANCE.DtoToDaoUser(user));
        log.debug("Saved user" + user);
        return savedUser.map(DaoToDtoUserMapperImpl.INSTANCE::DaoToDtoUser);
    }

    /**
     * метод сохранения списка переданных пользователей
     * @param userList
     * @return List<daoUserImpl>
     */
    @Override
    public List<UserImpl> saveAll(List<UserImpl> userList) {
        log.debug("save objects:" + userList);

        log.debug("saved users:" + userList);
        return ;
    }

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id
     * @return
     */
    @Override
    public Optional<com.simplekitchen.project.dao.entity.user.UserImpl> get(Long id) {
        return Optional.empty();
    }

    /**
     * метод получения сущностей всех пользователей
     * @return List<com.simplekitchen.project.dao.entity.user.UserImpl>
     */
    @Override
    public List<com.simplekitchen.project.dao.entity.user.UserImpl> getAll() {
        return null;
    }

    /**
     * метод получения списка пользователей по списку уникальных идентификаторов
     * @param ids
     * @return List<com.simplekitchen.project.dao.entity.user.UserImpl>
     */
    @Override
    public List<com.simplekitchen.project.dao.entity.user.UserImpl> getAllById(List<Long> ids) {
        return null;
    }

    /**
     * метод удаления пользователя по уникальному идентификатору
     * @param id
     * @return Boolean
     */
    @Override
    public Boolean deleteById(Long id) {
        return null;
    }

    /**
     * метод удаления польователя по его сущности
     * @param user
     * @return Boolean
     */
    @Override
    public Boolean delete(com.simplekitchen.project.dao.entity.user.UserImpl user) {
        return null;
    }

    /**
     * метод удаления пользователей по переданному списку пользователей
     * @param users
     * @return Boolean
     */
    @Override
    public Boolean deleteAll(List<com.simplekitchen.project.dao.entity.user.UserImpl> users) {
        return null;
    }

    /**
     * метод удаления всех имеющихся пользователей
     * @return Boolean
     */
    @Override
    public Boolean deleteAll() {
        return null;
    }
}
