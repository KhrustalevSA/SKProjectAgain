package com.simplekitchen.project.business.service.api;


import com.simplekitchen.project.dto.entity.user.UserImpl;

import java.util.List;
import java.util.Optional;

/**
 * интерфейс сервиса пользователей дао слоя
 * @author KhrustalevSA
 * @see com.simplekitchen.project.business.service.UserServiceImpl
 * @see UserImpl
 * @since 29.01.2023
 */
public interface UserService {

    /**
     * метод сохранения пользователя, возвращает сохраненного пользователя
     * @param user
     * @return Optional<UserImpl> user
     */
    Optional<UserImpl> save(UserImpl user);

    /**
     * метод сохранения всех пользователей, возвращает список сохраненных пользователей
     * @param users
     * @return List<UserImpl>
     */
    List<UserImpl> saveAll(List<UserImpl> users);

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id
     * @return Optional<UserImpl>
     */
    Optional<UserImpl> get(Long id);

    /**
     * метод получения всех существующих пользователей
     * @return List<UserImpl>
     */
    List<UserImpl> getAll();

    /**
     * метод для получения списка пользователей по их уникальным идентификаторам
     * @param ids
     * @return List<UserImpl>
     */
    List<UserImpl> getAllById(List<Long> ids);

    /**
     * удалить пользователя по его уникальному идентификатору
     * @param id
     * @return Boolean
     */
    Boolean deleteById(Long id);

    /**
     * метод для удаления пользователя по его полной сущности
     * @param user
     * @return Boolean
     */
    Boolean delete(UserImpl user);

    /**
     * метод для удаления списка пользователей из сущности пользователей
     * @param users
     * @return Boolean
     */
    Boolean deleteAll(List<UserImpl> users);

    /**
     * метод для удаления всех имеющихся пользователей
     * @return Boolean
     */
    Boolean deleteAll();

}
