package com.simplekitchen.project.dao.entity.user.api;

import java.util.List;

/**
 * интерфейс класса списка пользователей
 */
public interface UserList {

    /**
     * @return список пользователей
     */
    List<UserEntity> getUserEntityList();
}
