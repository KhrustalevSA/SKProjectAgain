package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.entity.common.api.LongList;
import com.simplekitchen.project.business.entity.user.UserImplListImpl;
import com.simplekitchen.project.business.entity.user.UserListImpl;
import com.simplekitchen.project.business.entity.user.api.UserList;
import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import com.simplekitchen.project.business.exception.*;
import com.simplekitchen.project.business.mapper.user.UserMapper;
import com.simplekitchen.project.business.service.api.UserControllerService;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.api.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс сервиса бизнес слоя, реализовывающий интерфейс UserControllerService
 * @see UserControllerService
 * @author KhrustalevSA
 * @since 29.01.2023
 * */
@Slf4j
@Service
@NoArgsConstructor
public class UserControllerServiceImpl implements UserControllerService {

    private static final String DELETE_RESULT = "Результат удаления: %b";
    private static final String REQUEST_NAME_AND_SURNAME =  "Переданное имя = %s, переданная фамилия = %s";

    /**
     * поле сервиса пользователя
     */
    private static UserService userService;

    /**
     * конструктор класса с параметром сервиса
     * @param userServiceDao
     */
    @Autowired
    public UserControllerServiceImpl(com.simplekitchen.project.dao.service.api.UserService userServiceDao) {
        userService = userServiceDao;
    }

    /**
     * метод сохранения пользователя с возвращением сохраненного пользователя
     * @param user
     * @return сохраненный пользователь
     */
    public User save(UserImpl user) throws DataBaseException, BaseException {
        try {
            validate(user);
            log.debug("Save" + user);
            UserEntity savedUserEntity = userService.save(UserMapper.INSTANCE.map(user));
            log.debug(String.format("Сохраненный пользователь = %s",user));
            return UserMapper.INSTANCE.map(savedUserEntity);
        } catch (Throwable e) {
            log.error(String.format("Не удалось сохранить пользователя %s",user));
            log.error(e.getMessage(),e.getCause());
            return UserImpl.builder().build();
        }
    }


    @Override
    public UserList saveAll(UserImplListImpl userList) throws BaseException, DataBaseException {
        try {
            validate(userList);
            log.debug(String.format("Запрошенный список пользователей = %s.", userList));
            com.simplekitchen.project.dao.entity.user.api.UserList userListDao = userService.saveAll(UserMapper.INSTANCE.map(userList));
            log.debug(String.format("Сохраненный список пользователй = %s.",userListDao));
            return UserMapper.INSTANCE.map(userListDao);
        } catch (Throwable e) {
            log.error(String.format("Не удалось созранить список пользователей %s.", userList));
            log.error(e.getMessage(),e.getCause());
            return null;
        }
    }

    /**
     * метод сохранения списка переданных пользователей
     * @param userList
     * @return UserListImpl
     */
    @Override
    public UserList saveAll(UserListImpl userList) throws BaseException, DataBaseException {
        try {
            validate(userList);
            log.debug(String.format("Запрошенный список пользователей = %s.", userList));
            com.simplekitchen.project.dao.entity.user.api.UserList userListDao = userService.saveAll((com.simplekitchen.project.dao.entity.user.api.UserList) UserMapper.INSTANCE.map(userList));
            log.debug(String.format("Сохраненный список пользователй = %s.",userListDao));
            return UserMapper.INSTANCE.map(userListDao);
        } catch (Throwable e) {
            log.error(String.format("Не удалось созранить список пользователей %s.", userList));
            log.error(e.getMessage(),e.getCause());
            return null;
        }
    }

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id
     * @return
     */
    @Override
    public User get(Long id) throws DataBaseException, BaseException {
        try {
            validate(id);
            log.debug(String.format("Полученный идентификаор %s",id));
            UserEntity foundUser = userService.findById(id);
            log.debug(String.format("Пользователь найденный по идентификатору %s = %s",id,foundUser));
            return UserMapper.INSTANCE.map(foundUser);
        } catch (Throwable e) {
            log.error(String.format("Не удалось найти пользователя по идентификатору %s",id));
            log.error(e.getMessage(),e.getCause());
            return null;
        }
    }

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param userInfo
     * @return Optional<UserEntityImpl>
     */
    @Override
    public UserList get(UserRequestInfo userInfo) throws BaseException {
        try {
            validate(userInfo);
            if (userInfo.getId() != null) {
                User user = get(userInfo.getId());
                log.debug(String.format("Пользователь по идентификатору = %s",user.getId()));
                return UserListImpl.builder().userList(Lists.newArrayList(user)).build();
            } else if (validate(userInfo.getName()) && validate(userInfo.getSurname())) {
                String receivedName = userInfo.getName();
                String receivedSurname = userInfo.getSurname();
                log.debug(String.format(REQUEST_NAME_AND_SURNAME,receivedName,receivedSurname));
                com.simplekitchen.project.dao.entity.user.api.UserList userListFoundByNameAndSurname =
                        userService.findByNameAndSurname(receivedName, receivedSurname);
                log.debug(String.format("Пользователи найденные по имени %s и фамилии %s = %s",
                        receivedName,receivedSurname,userListFoundByNameAndSurname));
                return UserMapper.INSTANCE.map(userListFoundByNameAndSurname);
            }
            return null;
        } catch (Throwable e) {
            log.error(String.format("Ошибка получения пользователя по инофрмации = %s",userInfo));
            log.error(e.getMessage(),e.getCause());
            return null;
        }
    }

    /**
     * метод получения сущностей всех пользователей
     * @return List<com.simplekitchen.project.dao.entity.user.UserEntityImpl>
     */
    @Override
    public UserList getAll() throws BaseException {
        try {
            com.simplekitchen.project.dao.entity.user.api.UserList foundAllUserList = userService.findAll();
            log.debug(String.format("Все имеющиеся пользователи = %s",foundAllUserList));
            return UserMapper.INSTANCE.map(foundAllUserList);
        } catch (Throwable e) {
            log.error("Ошибка получения списка всех пользователей");
            throw new GetException(e.getMessage(), e.getCause());
        }
    }

    /**
     * метод получения списка пользователей по списку уникальных идентификаторов
     * @param longList
     * @return List<com.simplekitchen.project.dao.entity.user.UserEntityImpl>
     */
    @Override
    public UserList getAllById(LongList longList) throws GetException {
        try {
            validate(longList);
            log.debug(String.format("Полученный список идентификаторов = %s",longList));
            com.simplekitchen.project.dao.entity.user.api.UserList foundAllUsersById =
                    userService.findAllById((com.simplekitchen.project.dao.entity.common.entity.api.LongList) longList);
            log.debug(String.format("Найденные пользователи = %s",foundAllUsersById));
            return UserMapper.INSTANCE.map(foundAllUsersById);
        } catch (Throwable e) {
            log.error(String.format
                    ("ошибка получения пользователей по списку уникальных идентификаторов = %s", longList));
            throw new GetException(e.getMessage(), e.getCause());
        }

    }

    /**
     * метод удаления пользователя по уникальному идентификатору
     * @param id
     * @return Boolean
     */
    @Override
    public Boolean deleteById(Long id) throws BaseException {
        try {
            validate(id);
            log.debug(String.format("Полученный идентификатор %s",id));
            Boolean deleteCheck = userService.deleteById(id);
            log.debug(String.format(DELETE_RESULT,deleteCheck));
            return deleteCheck;
        } catch (Throwable e) {
            log.error("Удаление пользователя по идентификатору = %s, не удалось");
            log.error(e.getMessage(),e.getCause());
            return false;
        }
    }

    @Override
    public Boolean deleteByNameAndSurname(UserRequestInfo userInfo) throws BaseException {
        try {
            validate(userInfo);
            if (userInfo.getId() != null) {
                Boolean deleteCheck = deleteById(userInfo.getId());
                log.debug(String.format("Результат удаления по идентификатору = %s",deleteCheck));
                return deleteCheck;
            } else if (validate(userInfo.getName()) && validate(userInfo.getSurname())) {
                String receivedName = userInfo.getName();
                String receivedSurname = userInfo.getSurname();
                log.debug(String.format(REQUEST_NAME_AND_SURNAME,receivedName,receivedSurname));
                Boolean deleteCheck = userService.deleteByNameAndSurname(receivedName, receivedSurname);
                log.debug(String.format(DELETE_RESULT,deleteCheck));
                return deleteCheck;
            }
            return null;
        } catch (Throwable e) {
            log.error(String.format("Ошибка удаления пользователя по инофрмации = %s",userInfo));
            throw new DeleteException(e.getMessage(), e.getCause());
        }
    }


    private Boolean validate(Object o) throws ValidationException {
        if (o == null) {
            throw new ValidationException("Некорректный запрос.");
        }
        return true;
    }

//    /**
//     * метод удаления польователя по пришедшей информации
//     * @param user
//     * @return Boolean
//     */
//    @Override
//    public Boolean delete(UserRequestInfo userRequestInfo) {
//        userService.delete()
//    }

}
