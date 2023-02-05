package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import com.simplekitchen.project.business.exception.UserNotFoundException;
import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.entity.user.api.User;
import com.simplekitchen.project.dao.entity.user.api.UserList;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.repository.UserRepository;
import com.simplekitchen.project.dao.service.api.UserService;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.hibernate.loader.plan.build.internal.returns.ScalarReturnImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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

    private static final String USER_NOT_FOUND_MESSAGE = "Пользователь не найден";
    private static final String USER_NOT_FOUND_BY_ID_MESSAGE = "Пользователь не найден по уникальному идентификатору %s";
    private static final String RECEIVED_USER_NAME_AND_SURNAME = "Запрошенные имя = %s и фамилия = %s";
    private static final String FOUND_USER = "Найденный пользователь = %s.";
    private static final String USER_NOT_FOUND_BY_NAME_AND_SURNAME = "Пользователь не найден по имени %s = и фамилии = %s";
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
    public User get(Long id) throws DataBaseException {
        try {
            log.debug("request id = " + id);
            User user = userRepository.findById(id).orElse(null);
            log.debug(String.format(FOUND_USER,user));
            return user;
        } catch (Exception e) {
            String errorMessage = String.format(USER_NOT_FOUND_BY_ID_MESSAGE, id);
            log.error(errorMessage);
            throw new DataBaseException(errorMessage);
        }    }

    /**
     * поиск пользователя по имеющейся инофрмации
     * @param userRequestInfo
     * @return Optional<UserImpl> сущность пользователя
     */
    @Override
    public UserList get(String name, String surname) {
        try {
            log.debug(String.format(RECEIVED_USER_NAME_AND_SURNAME,name,surname));
            List<User> userByNameAndSurname = userRepository.findByNameAndSurname(name, surname);
            log.debug(String.format(FOUND_USER,userByNameAndSurname));
            return userByNameAndSurname;
        } catch (Exception e) {
            String errorMessage = String.format(USER_NOT_FOUND_BY_NAME_AND_SURNAME,name,surname);
            log.error(errorMessage);
            throw new DataBaseException(errorMessage);
        }
    }

    public User findById(Long id) throws DataBaseException {
        try {
            log.debug("request id = " + id);
            User user = userRepository.findById(id).orElse(null);
            log.debug(String.format(FOUND_USER,user));
            return user;
        } catch (Exception e) {
            String errorMessage = String.format(USER_NOT_FOUND_BY_ID_MESSAGE, id);
            log.error(errorMessage);
            throw new DataBaseException(errorMessage);
        }
    }

    public List<User> findByNameAndSurname(@NonNull String name, @NonNull String surname) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_USER_NAME_AND_SURNAME,name,surname));
            List<User> userByNameAndSurname = userRepository.findByNameAndSurname(name, surname);
            log.debug(String.format(FOUND_USER,userByNameAndSurname));
            return userByNameAndSurname;
        } catch (Exception e) {
            String errorMessage = String.format(USER_NOT_FOUND_BY_NAME_AND_SURNAME,name,surname);
            log.error(errorMessage);
            throw new DataBaseException(errorMessage);
        }
    }

    /**
     * метод получения всех пользователей
     * @return список пользователей
     */
    @Override
    public Optional<List<UserImpl>> getAll() {
        return Optional.of(Lists.newArrayList(userRepository.findAll()));
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