package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.mapper.user.api.DaoToDtoUserMapper;
import com.simplekitchen.project.business.mapper.user.api.DaoToDtoUserMapperImpl;
import com.simplekitchen.project.business.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service(value = "UserServiceImplBusiness")
public class UserServiceImpl implements UserService {

    private static com.simplekitchen.project.dao.service.api.UserService userService;

    @Autowired
    public UserServiceImpl(com.simplekitchen.project.dao.service.api.UserService userServiceDao) {
        this.userService = userServiceDao;
    }

    public Optional<UserImpl> save(UserImpl user) {
        log.debug("Save" + user);
        Optional<com.simplekitchen.project.dao.entity.user.UserImpl> savedUser = userService.save(DaoToDtoUserMapper.INSTANCE.DtoToDaoUser(user));
        log.debug("Saved user" + user);
        return savedUser.map(DaoToDtoUserMapperImpl.INSTANCE::DaoToDtoUser);
    }

    @Override
    public List<com.simplekitchen.project.dao.entity.user.UserImpl> saveAll(List<com.simplekitchen.project.dao.entity.user.UserImpl> users) {
        return null;
    }

    @Override
    public Optional<com.simplekitchen.project.dao.entity.user.UserImpl> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<com.simplekitchen.project.dao.entity.user.UserImpl> getAll() {
        return null;
    }

    @Override
    public List<com.simplekitchen.project.dao.entity.user.UserImpl> getAllById(List<Long> ids) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }

    @Override
    public Boolean delete(com.simplekitchen.project.dao.entity.user.UserImpl user) {
        return null;
    }

    @Override
    public Boolean deleteAll(List<com.simplekitchen.project.dao.entity.user.UserImpl> users) {
        return null;
    }

    @Override
    public Boolean deleteAll() {
        return null;
    }
}
