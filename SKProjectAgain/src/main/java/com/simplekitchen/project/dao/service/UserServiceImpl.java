package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.repository.UserRepository;
import com.simplekitchen.project.dao.service.api.UserService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserImpl> save(UserImpl user) {
        return Optional.of(userRepository.save(user));
    }

    @Override
    public List<UserImpl> saveAll(List<UserImpl> users) {
        return Lists.newArrayList(userRepository.saveAll(users));
    }

    @Override
    public Optional<UserImpl> get(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserImpl> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public List<UserImpl> getAllById(List<Long> ids) {
        return Lists.newArrayList(userRepository.findAllById(ids));
    }

    @Override
    public Boolean deleteById(Long id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }

    @Override
    public Boolean delete(UserImpl user) {
        userRepository.delete(user);
        return !userRepository.findById(user.getId()).isPresent();
    }

    @Override
    public Boolean deleteAll(List<UserImpl> users) {
        userRepository.deleteAll(users);
        return Lists.newArrayList(userRepository.findAllById(users.stream().map(UserImpl::getId).collect(Collectors.toList()))).isEmpty();
    }

    @Override
    public Boolean deleteAll() {
        userRepository.deleteAll();
        return Lists.newArrayList(userRepository.findAll()).isEmpty();
    }

}
