package com.simplekitchen.project.business.entity.user;

import com.simplekitchen.project.business.entity.user.api.UserList;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.api.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserListImplTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testToString() {
        List<User> userList = new ArrayList<>();
        userList.add(UserImpl.builder().id(1L).name("Ivan").build());
        userList.add(UserImpl.builder().id(2L).name("Petr").build());
        UserList userListImpl = UserListImpl.builder().userList(userList).build();

        System.out.println(userListImpl.toString());

        Assert.assertTrue(true);
    }

    @Test
    public void getUserList() {
    }

    @Test
    public void setUserList() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void canEqual() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void builder() {
    }
}