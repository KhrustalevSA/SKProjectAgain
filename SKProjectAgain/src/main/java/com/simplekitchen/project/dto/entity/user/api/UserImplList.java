package com.simplekitchen.project.dto.entity.user.api;

import com.simplekitchen.project.dto.entity.user.UserImpl;

import java.util.List;

/**
 * Интерфейс для сущности списка рецептов
 * @author KhrustalevSA
 * @since 16.10.2022
 */
public interface UserImplList {
    /**
     * список пользователей
     */
    List<UserImpl> getUserList();
}
