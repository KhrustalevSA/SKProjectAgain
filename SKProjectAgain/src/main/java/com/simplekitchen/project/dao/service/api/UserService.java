package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.entity.user.api.User;
import com.simplekitchen.project.dao.entity.user.api.UserList;
import com.simplekitchen.project.dao.exception.DataBaseException;

import java.util.List;
import java.util.Optional;

/**
 * интерфейс сервиса пользователей
 * @author KhrustalevSA
 * @since 31.01.2023
 */
public interface UserService {

    /**
     * метод сохранения пользователя
     * @param user
     * @return сохраненный Optional объект пользователя
     */
    Optional<UserImpl> save(UserImpl user);

    /**
     * метод сохранения списка пользователей
     * @param userList
     * @return список сохраненных пользователей
     */
    List<UserImpl> saveAll(List<UserImpl> userList);

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id
     * @return Optional объект полученного пользователя
     */
    User get(Long id) throws DataBaseException;

    /**
     * метод получения пользователя по имени и фамилии
     * @param name
     * @param surname
     * @return список найденных пользователей
     */
    UserList get(String name, String surname);

    /**
     * метод получения всех пользователей
     * @return список пользователей
     */
    Optional<List<UserImpl>> getAll();

    /**
     * метод получения пользователей по уникальному идентификатору
     * @param ids
     * @return список пользователей
     */
    List<UserImpl> getAllById(List<Long> ids);

    /**
     * метод удаления пользователя по его уникальному идентификатору
     * @param id
     * @return Boolean объект
     */
    Boolean deleteById(Long id);

    /**
     * метод удаления пользователя по его сущности
     * @param user
     * @return Boolean объект
     */
    Boolean delete(UserImpl user);

    /**
     * метод удаления списка пользователей
     * @param userList
     * @return Boolean объект
     */
    Boolean deleteAll(List<UserImpl> userList);

    /**
     * метод удаления всех пользователей
     * @return Boolean объект
     */
    Boolean deleteAll();
}
