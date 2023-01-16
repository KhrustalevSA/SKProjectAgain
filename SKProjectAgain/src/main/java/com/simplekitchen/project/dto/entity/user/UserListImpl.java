package com.simplekitchen.project.dto.entity.user;


import com.simplekitchen.project.dto.entity.user.api.User;
import com.simplekitchen.project.dto.entity.user.api.UserList;
import lombok.*;

import java.util.List;

/**
 * Класс списка пользователей
 * @author KhrustalevSA
 * @since 16.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserListImpl implements UserList {

    private Long uuid;

    /**
     * список пользователей
     */
    private List<User> userList;
}
