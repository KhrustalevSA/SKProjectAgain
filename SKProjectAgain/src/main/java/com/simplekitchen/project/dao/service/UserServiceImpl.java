package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.common.entity.LongListImpl;
import com.simplekitchen.project.dao.entity.common.entity.api.LongList;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.UserImplListImpl;
import com.simplekitchen.project.dao.entity.user.UserListImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import com.simplekitchen.project.dao.entity.user.api.UserList;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.repository.UserRepository;
import com.simplekitchen.project.dao.service.api.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/**
 * класс сервиса пользователей
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Slf4j
@Service
@Data
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_MESSAGE = "Пользователь %s не найден";
    private static final String USER_NOT_FOUND_BY_ID_MESSAGE = "Пользователь не найден по уникальному идентификатору %s";
    private static final String RECEIVED_USER_ID = "Запрошенный уникальный идентификатор %s";
    private static final String RECEIVED_USER_NAME_AND_SURNAME = "Запрошенные имя = %s и фамилия = %s";
    private static final String FOUND_USER = "Найденный пользователь = %s.";
    private static final String USER_NOT_FOUND_BY_NAME_AND_SURNAME = "Пользователь не найден по имени %s = и фамилии = %s";
    private static final String USER_SAVED = "Сохраненный пользователь %s";
    private static final String USER_NOT_SAVED = "Пользователь не сохранился = %s";
    private static final String RECEIVED_USER_LIST = "Полученный список пользователей = %s";
    private static final String REQUESTED_USER_LIST = "Переданный список пользователей = %s";
    private static final String USER_LIST_NOT_SAVED = "Список пользователей не сохранился = %s";
    private static final String USER_LIST_NOT_FOUND = "Не получилось найти список пользователей = %s";
    private static final String USER_LIST_NOT_FOUND_BY_ID = "Не получилось найти список пользователей по идентификаторам = %s";
    private static final String RECEIVED_ID = "Полученный уникальный идентификатор = %s";
    private static final String DELETE_USER_FAILED_BY_ID = "Ошибка удаления пользователя по идентификатору = %s";
    private static final String RECEIVED_ID_LIST = "Пришедший список уникальных идентификаторов = %s";
    private static final String DELETE_USER_LIST_FAILED = "Удаление пользователей %s не удалось";
    private static final String DELETE_USER_LIST_BY_ID_FAILED = "Удаление пользователей по идентификаторам %s не удалось";


    /**
     * репозиторий пользователей
     */
    private final UserRepository userRepository;

    /**
     * конструктор сервиса с автоматическим подключением
     * @param userRepository - репозиторий пользователей
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * метод сохранения пользователя
     * @param user сущность пользователя
     * @return сохраненный объект пользователя
     */
    @Override
    public UserEntity save(UserEntityImpl user) throws DataBaseException {
        try {
            log.debug(String.format(FOUND_USER,user));
            UserEntityImpl savedUser = userRepository.save(user);
            log.debug(String.format(USER_SAVED,savedUser));
            return savedUser;
        } catch (Exception e) {
            log.error(String.format(USER_NOT_FOUND_MESSAGE,user));
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * метод сохранения списка пользователей
     * @param userList список пользователей
     * @return список сохраненных пользователей
     */
    @Override
    public UserList saveAll(UserList userList) throws DataBaseException {
        try {
            log.debug(String.format(REQUESTED_USER_LIST,userList));
            List<UserEntityImpl> receivedUserEntityList = (List<UserEntityImpl>) userRepository.saveAll(userList.getUserEntityList());
            log.debug(String.format(RECEIVED_USER_LIST, receivedUserEntityList));
            return UserListImpl.builder().userEntityList(receivedUserEntityList).build();
        } catch (Exception e) {
            log.error(String.format(USER_LIST_NOT_SAVED,userList));
            throw new DataBaseException(e.getMessage());
        }
    }
@Override
    public UserList saveAll(UserImplListImpl userList) throws DataBaseException {
        try {
            log.debug(String.format(REQUESTED_USER_LIST,userList));
            List<UserEntityImpl> receivedUserEntityList = (List<UserEntityImpl>) userRepository.saveAll(userList.getUserList());
            log.debug(String.format(RECEIVED_USER_LIST, receivedUserEntityList));
            return UserListImpl.builder().userEntityList(receivedUserEntityList).build();
        } catch (Exception e) {
            log.error(String.format(USER_LIST_NOT_SAVED,userList));
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id ункикальный идентификатор
     * @return объект полученного пользователя
     */
    @Override
    public UserEntity findById(Long id) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_USER_ID,id));
            UserEntity userEntity = userRepository.findById(id).orElse(null);
            log.debug(String.format(FOUND_USER, userEntity));
            return userEntity;
        } catch (Exception e) {
            log.error(String.format(USER_NOT_FOUND_BY_ID_MESSAGE, id));
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * поиск пользователя по имеющейся инофрмации
     * @param name имя пользователя
     * @param surname фамилия пользователя
     * @return список найденных пользователей
     */
    @Override
    public UserList findByNameAndSurname(String name, String surname) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_USER_NAME_AND_SURNAME,name,surname));
            List<UserEntityImpl> userEntityByNameAndSurname = userRepository.findByNameAndSurname(name, surname).orElse(null);
            log.debug(String.format(FOUND_USER, userEntityByNameAndSurname));
            return UserListImpl.builder().userEntityList(userEntityByNameAndSurname).build();
        } catch (Exception e) {
            log.error(String.format(USER_NOT_FOUND_BY_NAME_AND_SURNAME,name,surname));
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * метод получения всех пользователей
     * @return список всех пользователей
     */
    @Override
    public UserList findAll() throws DataBaseException {
        try {
            List<UserEntityImpl> userEntityList = (List<UserEntityImpl>) userRepository.findAll();
            log.debug(String.format(RECEIVED_USER_LIST, userEntityList));
            return UserListImpl.builder().userEntityList(userEntityList).build();
        } catch (Exception e) {
            log.error(USER_LIST_NOT_FOUND);
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * метод получения пользователей по уникальному идентификатору
     * @param longList список уникальных идентификаторов
     * @return список пользователей
     */
    @Override
    public UserList findAllById(LongList longList) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_ID_LIST,longList));
            List<UserEntityImpl> userEntityList = (List<UserEntityImpl>) userRepository.findAllById(longList.getLongList());
            log.debug(String.format(RECEIVED_USER_LIST, userEntityList));
            return UserListImpl.builder().userEntityList(userEntityList).build();
        } catch (Exception e) {
            log.error(String.format(USER_LIST_NOT_FOUND_BY_ID,longList));
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * метод удаления пользователя по его уникальному идентификатору
     * @param id уникальный идентификатор
     * @return Boolean объект
     */
    @Override
    public Boolean deleteById(Long id) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_ID,id));
            Optional<UserEntityImpl> userFoundById = userRepository.findById(id);
            log.debug(String.format("Удаляемый пользователь %s",userFoundById));
            userRepository.deleteById(id);
            userFoundById =  userRepository.findById(id);
            if (userFoundById.isPresent()) {
                log.debug(String.format("Удаление пользователя %s не удалось",userFoundById));
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error(String.format(DELETE_USER_FAILED_BY_ID,id));
            throw new DataBaseException(e.getMessage());
        }

    }

    /**
     * метод удаления пользователя имени и фамилии
     * @param name имя пользователя
     * @param surname фамилия пользователя
     * @return Boolean объект
     */
    @Override
    public Boolean deleteByNameAndSurname(String name, String surname) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_USER_NAME_AND_SURNAME,name,surname));
            List<UserEntityImpl> userEntityByNameAndSurname = userRepository.findByNameAndSurname(name, surname).orElse(null);
            log.debug(String.format(FOUND_USER, userEntityByNameAndSurname));
            userRepository.deleteAllByNameAndSurname(name, surname);
            userEntityByNameAndSurname = userRepository.findByNameAndSurname(name, surname).orElse(null);
            if (!userEntityByNameAndSurname.isEmpty()) {
                log.debug(String.format(DELETE_USER_LIST_FAILED, userEntityByNameAndSurname));
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error(String.format(USER_NOT_FOUND_BY_NAME_AND_SURNAME,name,surname));
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * метод удаления списка пользователей по их уникальным идентификаторам
     * @param longList список уникальных идентификаторов
     * @return Boolean объект
     */
    @Override
    public Boolean deleteAllById(@RequestBody LongListImpl longList) throws DataBaseException {
        String errorMessage = String.format(DELETE_USER_LIST_BY_ID_FAILED, longList);
        try {
            log.debug(String.format(RECEIVED_ID_LIST,longList));
            List<UserEntityImpl> userEntityList = (List<UserEntityImpl>) userRepository.findAllById(longList.getLongList());
            log.debug(String.format(RECEIVED_USER_LIST, userEntityList));
            userRepository.deleteAllById(longList.getLongList());
            userEntityList = (List<UserEntityImpl>) userRepository.findAllById(longList.getLongList());
            if (!userEntityList.isEmpty()) {
                log.debug(errorMessage);
                return false;
            }
            return false;
        } catch (Exception e) {
            log.debug(errorMessage);
            throw new DataBaseException(e.getMessage());
        }
    }

}