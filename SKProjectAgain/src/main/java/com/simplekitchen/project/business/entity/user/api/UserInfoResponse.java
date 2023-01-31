package com.simplekitchen.project.business.entity.user.api;


import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.user.api.UserList;

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
