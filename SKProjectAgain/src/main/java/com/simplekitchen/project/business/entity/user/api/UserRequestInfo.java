package com.simplekitchen.project.business.entity.user.api;

public interface UserRequestInfo {
    /**
     * @return уникальный идентификатор пользователя
     */
    Long getId();

    /**
     * @return имя пользователя
     */
    String getName();

    /**
     * @return фамилия пользователя
     */
    String getSurname();

}
