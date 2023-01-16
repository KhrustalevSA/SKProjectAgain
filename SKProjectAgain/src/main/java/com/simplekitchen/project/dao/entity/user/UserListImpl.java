package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.user.api.UserList;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserListImpl implements UserList {
    private List<UserImpl> userList;
}
