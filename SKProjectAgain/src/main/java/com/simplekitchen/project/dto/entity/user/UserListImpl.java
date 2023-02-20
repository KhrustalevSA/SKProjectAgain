package com.simplekitchen.project.dto.entity.user;

import com.simplekitchen.project.dto.entity.user.api.UserList;
import com.simplekitchen.project.dto.entity.user.api.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    /**
     * список пользователей
     */
    private List<User> userList;

    @Override
    public String toString() {
        return "UserListImpl{" +
                "userEntityList=" + userList +
                '}';
    }
}
