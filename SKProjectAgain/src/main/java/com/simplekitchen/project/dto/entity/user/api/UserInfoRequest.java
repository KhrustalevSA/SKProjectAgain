package com.simplekitchen.project.dto.entity.user.api;

public interface UserInfoRequest {
    /**
     * @return уникальный идентификатор пользователя
     */
    String getUuid();

    /**
     * @return имя пользователя
     */
    String getName();


}
