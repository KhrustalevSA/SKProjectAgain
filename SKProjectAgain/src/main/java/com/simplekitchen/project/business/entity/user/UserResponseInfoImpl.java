package com.simplekitchen.project.business.entity.user;



import com.simplekitchen.project.business.entity.user.api.UserResponseInfo;
import com.simplekitchen.project.business.entity.user.api.UserList;
import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.*;

import java.util.List;

/**
 * Класс для получения найденных из запроса поьзователей
 * @author KhrustalevSA
 * @since 16.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseInfoImpl implements UserResponseInfo {

    /**
     * поле статуса запроса
     */
    private StatusImpl status;

    /**
     * поле списка пользователей
     */
    private List<UserImpl> userList;
}
