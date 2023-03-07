package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.common.entity.api.LongList;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.repository.UserRepository;
import com.simplekitchen.project.dao.service.api.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private static final String RECEIVED_USER_NAME_AND_SURNAME_AND_PATRONYMIC = "Запрошенные имя = %s, фамилия = %s и отчество = %s";
    private static final String FOUND_USER = "Найденный пользователь = %s.";
    private static final String USER_NOT_FOUND_BY_NAME_AND_SURNAME = "Пользователь не найден по имени = %s и фамилии = %s";
    private static final String USER_NOT_FOUND_BY_NAME_AND_SURNAME_AND_PATRONYMIC = "Пользователь не найден по имени = %s, фамилии = %s и отчество = %s";
    private static final String USER_SAVED = "Сохраненный пользователь (Дао) %s";
    private static final String USER_NOT_SAVED = "Пользователь не сохранился = %s";
    private static final String RECEIVED_USER_LIST = "Полученный список пользователей = %s";
    private static final String REQUESTED_USER_LIST = "Переданный список пользователей = %s";
    private static final String USER_LIST_NOT_SAVED = "Список пользователей не сохранился = %s";
    private static final String USER_LIST_NOT_FOUND = "Не получилось найти список пользователей = %s";
    private static final String USER_LIST_NOT_FOUND_BY_ID = "Не получилось найти список пользователей по идентификаторам = %s";
    private static final String RECEIVED_ID = "Полученный уникальный идентификатор = %s";
    private static final String DELETE_USER_FAILED_BY_ID = "Ошибка удаления пользователя по идентификатору = %s";
    private static final String RECEIVED_ID_LIST = "Пришедший список уникальных идентификаторов = %s";
    private static final String DELETE_USER_LIST_FAILED = "Удаление пользователей по имени %s и фамилии %s не удалось";
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
            log.debug(String.format("Сохраняемый пользователь %s",user));
            UserEntityImpl savedUser = userRepository.save(user);
            log.debug(String.format(USER_SAVED, savedUser));
            return savedUser;
        } catch (Exception e) {
            log.error(String.format("Ошибка сохранения пользователя %s в БД",user));
            throw new DataBaseException(e.getMessage(), e.getCause());
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
            return userRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(String.format(USER_NOT_FOUND_BY_ID_MESSAGE, id));
            throw new DataBaseException(e.getMessage(), e.getCause());
        }
    }

    /**
     * поиск пользователя по имеющейся инофрмации
     * @param name имя пользователя
     * @param surname фамилия пользователя
     * @return список найденных пользователей
     */
    @Override
    public List<UserEntity> findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic) throws DataBaseException {
        try {
            Optional<List<UserEntity>> userEntityByNameAndSurnameAndPatronymic = StringUtils.isNotEmpty(patronymic)
                    ? userRepository.findByNameAndSurnameAndPatronymic(name, surname, patronymic)
                    : userRepository.findByNameAndSurname(name, surname);
            log.debug(String.format(FOUND_USER, userEntityByNameAndSurnameAndPatronymic));
            return userEntityByNameAndSurnameAndPatronymic.orElse(null);
        } catch (Exception e) {
            log.error(String.format(USER_NOT_FOUND_BY_NAME_AND_SURNAME_AND_PATRONYMIC, name, surname, patronymic));
            throw new DataBaseException(e.getMessage(),e.getCause());
        }
    }

    /**
     * метод получения всех пользователей
     * @return список всех пользователей
     */
    @Override
    public List<UserEntity> findAll() throws DataBaseException {
        try {
            List<UserEntity> userEntityList = new ArrayList<>();
            userRepository.findAll().forEach(userEntityList::add);
            log.debug(String.format(RECEIVED_USER_LIST, userEntityList));
            return userEntityList;
        } catch (Exception e) {
            log.error(USER_LIST_NOT_FOUND);
            throw new DataBaseException(e.getMessage(), e.getCause());
        }
    }

    /**
     * метод получения пользователей по уникальному идентификатору
     * @param longList список уникальных идентификаторов
     * @return список пользователей
     */
    @Override
    public List<UserEntity> findAllById(LongList longList) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_ID_LIST,longList));
            List<UserEntity> userEntityList = new ArrayList<>();
            userRepository.findAllById(longList.getLongList()).forEach(userEntityList::add);
            log.debug(String.format(RECEIVED_USER_LIST, userEntityList));
            return userEntityList;
        } catch (Exception e) {
            log.error(String.format(USER_LIST_NOT_FOUND_BY_ID,longList));
            throw new DataBaseException(e.getMessage(), e.getCause());
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
            if (userFoundById.isPresent()) {
                userRepository.deleteById(id);
                return true;
            } else {
                log.debug(String.format("Не удалось найти пользователя с идентификатором %s для удаления.", id));
                return false;
            }
        } catch (Exception e) {
            log.error(String.format(DELETE_USER_FAILED_BY_ID,id));
            throw new DataBaseException(e.getMessage(), e.getCause());
        }
    }
}