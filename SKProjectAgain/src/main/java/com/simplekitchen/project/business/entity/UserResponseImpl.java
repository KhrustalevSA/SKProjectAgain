package com.simplekitchen.project.business.entity;


import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.business.entity.user.api.UserInfoResponse;
import com.simplekitchen.project.dto.entity.user.api.UserList;
import lombok.*;

/**
 * Класс для получения найденных из запроса поьзователей
 * @author KhrustalevSA
 * @since 16.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseImpl implements UserInfoResponse {

    /**
     * поле статуса запроса
     */
    private StatusImpl status;

    /**
     * поле списка пользователей
     */
    private UserList userList;
}
