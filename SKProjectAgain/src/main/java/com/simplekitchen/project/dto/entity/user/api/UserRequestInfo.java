package com.simplekitchen.project.dto.entity.user.api;

/**
 * интерфейс запроса пользователя
 * @author KhrustalevSA
 * @since 07.03.2023
 */
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

    /**
     * метод получения отчества
     * @return отчество полльзователя
     */
    String getPatronymic();

}
