package com.simplekitchen.project.dto.entity.user.api;

import com.simplekitchen.project.dto.entity.user.UserImpl;

import java.util.List;

public interface UserImplList {
    /**
     * список пользователей
     */
    List<UserImpl> getUserList();
}
