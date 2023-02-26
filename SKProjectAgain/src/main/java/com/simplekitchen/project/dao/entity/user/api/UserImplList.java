package com.simplekitchen.project.dao.entity.user.api;

import com.simplekitchen.project.dao.entity.user.UserEntityImpl;

import java.util.List;

/**
 * интерфейс списка класса сущности пользователя
 * @author KhrustalevSA
 * @since 26.02.2023
 */
public interface UserImplList {
    /**
     * список пользователей
     */
    List<UserEntityImpl> getUserList();
}
