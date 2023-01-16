package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.service.api.UserService;

public class UserServiceImpl extends ServiceBase<UserImpl> implements UserService {

    public UserServiceImpl() {
        super(UserImpl.class);
    }
}
