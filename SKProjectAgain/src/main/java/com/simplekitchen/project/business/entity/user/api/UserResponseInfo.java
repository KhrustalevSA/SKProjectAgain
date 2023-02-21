package com.simplekitchen.project.business.entity.user.api;


import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.dto.entity.user.api.User;

import java.util.List;

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
