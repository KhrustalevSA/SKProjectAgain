package com.simplekitchen.project.business.entity.user;

import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import lombok.*;

/**
 * Класс для легкого поиска пользователей
 * @author KhrustalevSA
 * @since 16.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequestInfoImpl implements UserRequestInfo {

    /**
     * уникальный идентификатор пользователя
     */
    private Long id;

    /**
     * имя пользователя
     */
    private String name;

    /**
     * фамилия пользователя
     */
    private String surname;

}
