package com.simplekitchen.project.dto.entity.user;


import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.user.api.UserInfoResponse;
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
public class UserInfoResponseImpl implements UserInfoResponse {

    /**
     * поле статуса запроса
     */
    private StatusImpl status;

    /**
     * поле списка пользователей
     */
    private UserList userList;
}
