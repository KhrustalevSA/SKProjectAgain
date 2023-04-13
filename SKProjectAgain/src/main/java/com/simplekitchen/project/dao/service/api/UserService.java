package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.common.api.LongList;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import com.simplekitchen.project.dao.exception.DataBaseException;

import java.util.List;

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

    UserEntity register(UserEntityImpl user);

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
    List<UserEntity> findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic) throws DataBaseException;

    /**
     * метод получения всех пользователей
     * @return список пользователей
     */
    List<UserEntity> findAll() throws DataBaseException;

    /**
     * метод получения пользователей по уникальному идентификатору
     * @param longList список идентификаторов
     * @return список пользователей
     */
    List<UserEntity> findAllById(LongList longList) throws DataBaseException;

    /**
     * метод удаления пользователя по его уникальному идентификатору
     * @param id уникальный идентификатор
     * @return Boolean объект
     */
    Boolean deleteById(Long id) throws DataBaseException;

    UserEntity findByUsername(String name);
}
