package com.simplekitchen.project.dao.entity.user.api;

import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;

import java.util.List;

public interface UserImplList {
    /**
     * список пользователей
     */
    List<UserEntityImpl> getUserList();
}
