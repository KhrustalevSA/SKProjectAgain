package com.simplekitchen.project.business.service.api;


import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.exception.GetException;
import com.simplekitchen.project.business.service.UserControllerServiceImpl;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.api.User;
import com.simplekitchen.project.dto.entity.user.api.UserRequestInfo;
import com.simplekitchen.project.dto.entity.user.api.UserResponseInfo;

import java.util.List;

/**
 * интерфейс сервиса пользователей дао слоя
 * @author KhrustalevSA
 * @see UserControllerServiceImpl
 * @see UserImpl
 * @since 29.01.2023
 */
public interface UserControllerService {

    /**
     * метод сохранения пользователя
     * @param user объект пользователя
     * @return класс информации с сохраненным пользователем и статусом
     */
    UserResponseInfo save(UserImpl user) throws Throwable;

    /**
     * метод получения пользователя по классу информации
     * @param userInfo информация о пользователе
     * @return список пользователей
     */
    List<User> get(UserRequestInfo userInfo) throws Throwable;

    /**
     * метод получения всех существующих пользователей
     * @return список пользователей
     */
    List<User> getAll() throws Throwable;

    /**
     * метод для получения списка пользователей по их уникальным идентификаторам
     * @param longList список уникальных идентификаторов
     * @return список пользователей
     */
    List<User> getAllById(LongListImpl longList) throws GetException;

    /**
     * удалить пользователя по его уникальному идентификатору
     * @param id уникальный идентификатор пользователя
     * @return логический ответ
     */
    Boolean deleteById(Long id) throws BaseException;


}
