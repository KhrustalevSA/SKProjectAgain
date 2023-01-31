package com.simplekitchen.project.dao.entity.user.api;

import com.simplekitchen.project.dao.entity.user.UserImpl;

import java.util.List;

/**
 * интерфейс класса списка пользователей
 */
public interface UserList {

    /**
     * @return список пользователей
     */
    List<UserImpl> getUserList();
}
