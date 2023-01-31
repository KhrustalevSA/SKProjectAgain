package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.user.api.UserList;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * класс списка пользователей
 */
@Data
@Builder
public class UserListImpl implements UserList {

    /**
     * поле список пользователей
     */
    private List<UserImpl> userList;
}
