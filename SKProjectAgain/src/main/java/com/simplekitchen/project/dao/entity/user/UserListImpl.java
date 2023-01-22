package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.user.api.UserList;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;


public class UserListImpl implements UserList {
    private List<UserImpl> userList;

    public UserListImpl() {
    }

    public UserListImpl(List<UserImpl> userList) {
        this.userList = userList;
    }

    @Override
    public List<UserImpl> getUserList() {
        return userList;
    }

    public void setUserList(List<UserImpl> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserListImpl)) return false;
        UserListImpl userList1 = (UserListImpl) o;
        return Objects.equals(getUserList(), userList1.getUserList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserList());
    }

    @Override
    public String toString() {
        return "UserListImpl{" +
                "userList=" + userList +
                '}';
    }
}
