package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.user.UserImpl;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserImpl> save(UserImpl user);

    List<UserImpl> saveAll(List<UserImpl> users);

    Optional<UserImpl> get(Long id);

    List<UserImpl> getAll();

    List<UserImpl> getAllById(List<Long> ids);

    Boolean deleteById(Long id);

    Boolean delete(UserImpl user);

    Boolean deleteAll(List<UserImpl> users);

    Boolean deleteAll();
}
