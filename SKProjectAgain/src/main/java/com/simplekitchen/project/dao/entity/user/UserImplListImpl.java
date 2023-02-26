package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.user.api.UserImplList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * класс списка класса сущности пользователя
 * @author KhrustalevSA
 * @since 26.02.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserImplListImpl implements UserImplList {

    /**
     * список пользователей
     */
    private List<UserEntityImpl> userList;

    @Override
    public String toString() {
        return "UserListImpl{" +
                "userEntityList=" + userList +
                '}';
    }
}
