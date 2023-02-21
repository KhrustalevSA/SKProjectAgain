package com.simplekitchen.project.dto.entity.user;

import com.simplekitchen.project.dto.entity.user.api.UserImplList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserImplListImpl implements UserImplList {
    /**
     * список пользователей
     */
    private List<UserImpl> userList;

    @Override
    public String toString() {
        return "UserListImpl{" +
                "userEntityList=" + userList +
                '}';
    }
}
