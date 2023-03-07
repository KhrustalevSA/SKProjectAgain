package com.simplekitchen.project.dto.entity.user.api;


import com.simplekitchen.project.dto.common.StatusImpl;

import java.util.List;

/**
 * интерфейс ответа о полученной информации о пользователя
 * @author KhrustalevSA
 * @since 07.03.2023
 */
public interface UserResponseInfo {
    /**
     * @return статус ответа
     */
    StatusImpl getStatus();

    /**
     * @return найденные пользователи
     */
    List<User> getUserList();
}
