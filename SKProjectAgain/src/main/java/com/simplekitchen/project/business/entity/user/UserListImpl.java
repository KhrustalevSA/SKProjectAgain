package com.simplekitchen.project.business.entity.user;

import com.simplekitchen.project.business.entity.user.api.UserList;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

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
    private List<UserImpl> userList;

    /**
     * метод добавляет пользователя в список
     * @param user
     */
    public Boolean add(Optional<UserImpl> user) {
        if (user.isPresent()) {
            userList.add(user.get());
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "UserListImpl{" +
                "userList=" + userList +
                '}';
    }
}
