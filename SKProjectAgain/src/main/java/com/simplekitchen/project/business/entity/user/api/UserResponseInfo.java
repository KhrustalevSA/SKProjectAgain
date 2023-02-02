package com.simplekitchen.project.business.entity.user.api;


import com.simplekitchen.project.business.entity.common.StatusImpl;

public interface UserResponseInfo {
    /**
     * @return статус ответа
     */
    StatusImpl getStatus();

    /**
     * @return найденные пользователи
     */
    UserList getUserList();
}
