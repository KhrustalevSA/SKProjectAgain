package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.repository.UserRepository;
import com.simplekitchen.project.dao.service.api.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * класс сервиса пользователей
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Slf4j
@Service
@Data
public class UserServiceImpl implements UserService {

    /**
     * репозиторий пользователей
     */
    private final UserRepository userRepository;

    /**
     * конструктор сервиса с автоматическим подключением
     * @param userRepository
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * метод сохранения пользователя
     * @param user
     * @return сохраненный Optional объект пользователя
     */
    @Override
    public Optional<UserImpl> save(UserImpl user) {
        log.debug("Save" + user);
        Optional<UserImpl> savedUser = Optional.of(userRepository.save(user));
        log.debug("Saved user" + user);
        return savedUser;
    }

    /**
     * метод сохранения списка пользователей
     * @param userList
     * @return список сохраненных пользователей
     */
    @Override
    public List<UserImpl> saveAll(List<UserImpl> userList) {
        return Lists.newArrayList(userRepository.saveAll(userList));
    }

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id
     * @return Optional объект полученного пользователя
     */
    @Override
    public Optional<UserImpl> get(Long id) {
        return userRepository.findById(id);
    }

    /**
     * метод получения всех пользователей
     * @return список пользователей
     */
    @Override
    public List<UserImpl> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    /**
     * метод получения пользователей по уникальному идентификатору
     * @param ids
     * @return список пользователей
     */
    @Override
    public List<UserImpl> getAllById(List<Long> ids) {
        return Lists.newArrayList(userRepository.findAllById(ids));
    }

    /**
     * метод удаления пользователя по его уникальному идентификатору
     * @param id
     * @return Boolean объект
     */
    @Override
    public Boolean deleteById(Long id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }

    /**
     * метод удаления пользователя по его сущности
     * @param user
     * @return Boolean объект
     */
    @Override
    public Boolean delete(UserImpl user) {
        userRepository.delete(user);
        return !userRepository.findById(user.getId()).isPresent();
    }

    /**
     * метод удаления списка пользователей
     * @param userList
     * @return Boolean объект
     */
    @Override
    public Boolean deleteAll(List<UserImpl> userList) {
        userRepository.deleteAll(userList);
        return Lists.newArrayList(userRepository.findAllById(userList.stream().map(UserImpl::getId).collect(Collectors.toList()))).isEmpty();
    }

    /**
     * метод удаления всех пользователей
     * @return Boolean объект
     */
    @Override
    public Boolean deleteAll() {
        userRepository.deleteAll();
        return Lists.newArrayList(userRepository.findAll()).isEmpty();
    }

}