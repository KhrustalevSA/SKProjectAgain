package com.simplekitchen.project.business.entity;

import com.simplekitchen.project.business.entity.user.api.UserInfoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс для легкого поиска пользователей
 * @author KhrustalevSA
 * @since 16.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestImpl implements UserInfoRequest {

    /**
     * @return уникальный идентификатор пользователя
     */
    private String uuid;

    /**
     * @return имя пользователя
     */
    private String name;
}
