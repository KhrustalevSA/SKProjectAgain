package com.simplekitchen.project.dto.entity.user;

import com.simplekitchen.project.dto.entity.user.api.UserList;
import com.simplekitchen.project.dto.entity.user.api.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс списка пользователей
 * @author KhrustalevSA
 * @since 16.10.2022
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserListImpl implements UserList {

    /**
     * список пользователей
     */
    private List<User> userList;

    public List<User> getUserList() {
        if(userList == null){
            userList = new ArrayList<>();
        }
        return userList;
    }
}
