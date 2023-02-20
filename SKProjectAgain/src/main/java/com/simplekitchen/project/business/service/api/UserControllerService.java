package com.simplekitchen.project.business.service.api;


import com.simplekitchen.project.business.entity.common.api.LongList;
import com.simplekitchen.project.dto.entity.user.UserImplListImpl;
import com.simplekitchen.project.dto.entity.user.UserListImpl;
import com.simplekitchen.project.dto.entity.user.api.UserList;
import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import com.simplekitchen.project.business.exception.*;
import com.simplekitchen.project.business.service.UserControllerServiceImpl;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.api.User;

/**
 * интерфейс сервиса пользователей дао слоя
 * @author KhrustalevSA
 * @see UserControllerServiceImpl
 * @see UserImpl
 * @since 29.01.2023
 */
public interface UserControllerService {

    /**
     * метод сохранения пользователя, возвращает сохраненного пользователя
     * @param user
     * @return Optional<UserEntityImpl> user
     */
    User save(UserImpl user) throws DataBaseException, BaseException;

    /**
     * метод сохранения всех пользователей, возвращает список сохраненных пользователей
     * @param userList
     * @return List<UserEntityImpl>
     */
    UserList saveAll(UserImplListImpl userList) throws BaseException, DataBaseException;

    /**
     * метод сохранения всех пользователей, возвращает список сохраненных пользователей
     * @param users
     * @return List<UserEntityImpl>
     */
    UserList saveAll(UserListImpl users) throws BaseException, DataBaseException;

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id
     * @return Optional<UserEntityImpl>
     */
    User get(Long id) throws DataBaseException, BaseException;

    /**
     * метод получения пользователя по классу информации
     * @param userInfo
     * @return Optional<UserEntityImpl>
     */
    UserList get(UserRequestInfo userInfo) throws BaseException;

    /**
     * метод получения всех существующих пользователей
     * @return List<UserEntityImpl>
     */
    UserList getAll() throws BaseException;

    /**
     * метод для получения списка пользователей по их уникальным идентификаторам
     * @param longList
     * @return List<UserEntityImpl>
     */
    UserList getAllById(LongList longList) throws GetException;

    /**
     * удалить пользователя по его уникальному идентификатору
     * @param id
     * @return Boolean
     */
    Boolean deleteById(Long id) throws BaseException;

    Boolean deleteByNameAndSurname(UserRequestInfo userInfo) throws BaseException;


}
