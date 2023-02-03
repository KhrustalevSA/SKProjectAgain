package com.simplekitchen.project.business.entity.user.api;


import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;

import java.util.List;

public interface UserResponseInfo {
    /**
     * @return статус ответа
     */
    StatusImpl getStatus();

    /**
     * @return найденные пользователи
     */
    List<UserImpl> getUserList();
}
