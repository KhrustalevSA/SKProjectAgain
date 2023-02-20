package com.simplekitchen.project.dao.entity.user.api;

import com.simplekitchen.project.dao.entity.user.UserEntityImpl;

import java.util.List;

public interface UserImplList {
    /**
     * список пользователей
     */
    List<UserEntityImpl> getUserList();
}
