package com.simplekitchen.project.dao.entity.user.api;

import com.simplekitchen.project.dao.entity.user.UserImpl;

import java.util.List;

public interface UserList {
    /**
     * список пользователей
     */
    List<UserImpl> getUserList();
}
