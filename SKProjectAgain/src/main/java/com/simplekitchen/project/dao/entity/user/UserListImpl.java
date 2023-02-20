package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.user.api.UserList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * класс списка пользователей
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserListImpl implements UserList {

    /**
     * поле список пользователей
     */
    private List<UserEntityImpl> userEntityList;

    @Override
    public String toString() {
        return "UserListImpl{" +
                "userEntityList=" + userEntityList +
                '}';
    }
}
