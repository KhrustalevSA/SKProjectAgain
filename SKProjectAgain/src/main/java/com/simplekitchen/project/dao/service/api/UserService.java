package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.common.entity.api.LongList;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import com.simplekitchen.project.dao.entity.user.api.UserList;
import com.simplekitchen.project.dao.exception.DataBaseException;

/**
 * интерфейс сервиса пользователей
 * @author KhrustalevSA
 * @since 31.01.2023
 */
public interface UserService {

    /**
     * метод сохранения пользователя
     * @param user сущность пользователя
     * @return сохраненный Optional объект пользователя
     */
    UserEntity save(UserEntityImpl user) throws DataBaseException;

    /**
     * метод сохранения списка пользователей
     * @param userList список пользователей
     * @return список сохраненных пользователей
     */
    UserList saveAll(UserList userList) throws DataBaseException;

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id уникальный идентификатор
     * @return Optional объект полученного пользователя
     */
    UserEntity findById(Long id) throws DataBaseException;

    /**
     * метод получения пользователя по имени и фамилии
     * @param name имя пользователя
     * @param surname фамилия пользователя
     * @return список найденных пользователей
     */
    UserList findByNameAndSurname(String name, String surname) throws DataBaseException;

    /**
     * метод получения всех пользователей
     * @return список пользователей
     */
    UserList findAll() throws DataBaseException;

    /**
     * метод получения пользователей по уникальному идентификатору
     * @param longList список идентификаторов
     * @return список пользователей
     */
    UserList findAllById(LongList longList) throws DataBaseException;

    /**
     * метод удаления пользователя по его уникальному идентификатору
     * @param id уникальный идентификатор
     * @return Boolean объект
     */
    Boolean deleteById(Long id) throws DataBaseException;

    /**
     * метод удаления пользователя по его сущности
     * @param name имя пользователя
     * @param surname фамилия пользователя
     * @return Boolean объект
     */
    Boolean deleteByNameAndSurname(String name, String surname) throws DataBaseException ;

    /**
     * метод удаления списка пользователей
     * @param longList список идентификаторов
     * @return Boolean объект
     */
    Boolean deleteAllById(LongList longList) throws DataBaseException ;

}
