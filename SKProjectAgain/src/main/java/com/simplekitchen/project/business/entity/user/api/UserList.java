package com.simplekitchen.project.business.entity.user.api;


import com.simplekitchen.project.dto.entity.user.UserImpl;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для сущности списка пользователей
 * @author KhrustalevSA
 * @since 16.10.2022
 */
public interface UserList {
    /**
     * список пользователей
     */
    List<UserImpl> getUserList();

    /**
     * добавить пользователя в список
     */
    Boolean add(Optional<UserImpl> user);
}
