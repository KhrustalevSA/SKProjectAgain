package com.simplekitchen.project.dto.entity.user.api;


import com.simplekitchen.project.dto.common.StatusImpl;

public interface UserInfoResponse {
    /**
     * @return статус ответа
     */
    StatusImpl getStatus();

    /**
     * @return найденные пользователи
     */
    UserList getUserList();
}
