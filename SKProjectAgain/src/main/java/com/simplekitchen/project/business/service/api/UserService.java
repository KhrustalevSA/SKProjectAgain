package com.simplekitchen.project.business.service.api;


import com.simplekitchen.project.business.entity.user.UserListImpl;
import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import com.simplekitchen.project.business.entity.user.api.UserResponseInfo;
import com.simplekitchen.project.business.exception.UserNotFoundException;
import com.simplekitchen.project.business.exception.UserResponseInfoNotFoundException;
import com.simplekitchen.project.business.exception.ValidationException;
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
    UserListImpl saveAll(UserListImpl users);

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id
     * @return Optional<UserImpl>
     */
    Optional<UserImpl> get(Long id);

    /**
     * метод получения пользователя по классу информации
     * @param userInfo
     * @return Optional<UserImpl>
     */
    UserResponseInfo get(UserRequestInfo userInfo) throws UserNotFoundException, ValidationException;

    /**
     * метод получения всех существующих пользователей
     * @return List<UserImpl>
     */
    Optional<List<UserImpl>> getAll() throws UserResponseInfoNotFoundException;

//    /**
//     * метод для получения списка пользователей по их уникальным идентификаторам
//     * @param userList
//     * @return List<UserImpl>
//     */
//    UserList getAllById(UserList userList);

    /**
     * удалить пользователя по его уникальному идентификатору
     * @param id
     * @return Boolean
     */
    Boolean deleteById(Long id);

//    /**
//     * метод для удаления пользователя по его полной сущности
//     * @param user
//     * @return Boolean
//     */
//    Boolean delete(UserImpl user);


}
