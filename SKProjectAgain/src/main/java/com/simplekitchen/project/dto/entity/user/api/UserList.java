package com.simplekitchen.project.dto.entity.user.api;

import java.util.List;

/**
 * Интерфейс для сущности списка пользователей
 * @author KhrustalevSA
 * @since 16.10.2022
 */
public interface UserList {
    /**
     * список пользователей
     */
    List<User> getUserList();
}
