package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.entity.common.LongListImpl;
import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.business.entity.user.UserResponseInfoImpl;
import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import com.simplekitchen.project.business.entity.user.api.UserResponseInfo;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.mapper.common.CommonMapper;
import com.simplekitchen.project.business.mapper.user.UserMapper;
import com.simplekitchen.project.business.service.api.UserControllerService;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import com.simplekitchen.project.dao.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.api.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс сервиса бизнес слоя, реализовывающий интерфейс UserControllerService
 *
 * @author KhrustalevSA
 * @see UserControllerService
 * @since 29.01.2023
 */
@Slf4j
@Service
@NoArgsConstructor
public class UserControllerServiceImpl implements UserControllerService {

    private static final String DELETE_RESULT = "Результат удаления: %b";
    private static final String REQUEST_NAME_AND_SURNAME_AND_PATRONYMIC =
            "Переданное имя = %s, переданная фамилия = %s, переданное отчество = %s";

    /**
     * поле сервиса пользователя
     */
    private static UserService userService;

    /**
     * конструктор класса с параметром сервиса
     * @param userServiceDao ДАО сервис работы с пользователем
     */
    @Autowired
    public UserControllerServiceImpl(UserService userServiceDao) {
        userService = userServiceDao;
    }

    /**
     * метод сохранения пользователя
     * @param user объект пользователя
     * @return класс информации с сохраненным пользователем и статусом
     * @throws Throwable ошибка
     */
    public UserResponseInfo save(@NonNull UserImpl user) throws Throwable {
        try {
            ObjectUtils.requireNonEmpty(user,"Передан пустой объект пользователя.");
            log.debug(String.format("Сохраняемы пользователь %s.", user));
            UserEntity savedUserEntity = userService.save(UserMapper.INSTANCE.map(user));
            log.debug(String.format("Сохраненный пользователь = %s.", user));
            return UserResponseInfoImpl.builder()
                    .userList(Lists.newArrayList(UserMapper.INSTANCE.map(savedUserEntity)))
                    .status(StatusImpl.builder().success(true).build())
                    .build();
        } catch (Throwable e) {
            log.error(String.format("Не удалось сохранить пользователя %s.", user));
            log.error(e.getMessage(), e.getCause());
            return UserResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(false).description(e.getMessage()).build())
                    .build();
        }
    }

    /**
     * метод получения пользователей по уникальному идентификатору или фамилии, имени и отчеству
     * @param userRequestInfo запрос информации
     * @return список пользователей
     */
    @Override
    public List<User> get(UserRequestInfo userRequestInfo) throws Throwable {
        ObjectUtils.requireNonEmpty(userRequestInfo, "Запрос пустой.");
        if (userRequestInfo.getId() != null) {
            return Lists.newArrayList(getById(userRequestInfo.getId()));
        } else if (StringUtils.isNotBlank(userRequestInfo.getName()) &&
                   StringUtils.isNotBlank(userRequestInfo.getSurname()) &&
                   StringUtils.isNotBlank(userRequestInfo.getPatronymic())) {
            return getByNameAndSurnameAndPatronymic(userRequestInfo.getName(),
                                                    userRequestInfo.getSurname(),
                                                    userRequestInfo.getPatronymic());
        }
        return null;
    }

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id уникальный идентификатор пользователя
     * @return найденный пользователь
     * @throws Throwable ошибка
     */
    protected User getById(@NonNull Long id) throws Throwable {
        ObjectUtils.requireNonEmpty(id, "Передан пустой идентификатор.");
        log.debug(String.format("Полученный идентификаор %s.", id));
        UserEntity foundUser = userService.findById(id);
        log.debug(String.format("Пользователь, найденный по идентификатору %s = %s.", id, foundUser));
        return UserMapper.INSTANCE.map(foundUser);
    }

    /**
     * метод нахождения списка пользователей по имени, фамилии и отчеству
     * @param name имя пользователя
     * @param surname фамилия пользователя
     * @param patronymic отчество пользователя
     * @return список найденных пользователей
     * @throws Throwable ошибка
     */
    protected List<User> getByNameAndSurnameAndPatronymic(@NonNull String name,
                                             @NonNull String surname,
                                             @NonNull String patronymic) throws Throwable {
        ObjectUtils.requireNonEmpty(name, "Передано пустое значение имени пользователя.");
        ObjectUtils.requireNonEmpty(surname, "Передано пустое значение фамилии пользователя.");
        ObjectUtils.requireNonEmpty(patronymic, "Передано пустое значение отчества пользователя.");
        log.debug(String.format(REQUEST_NAME_AND_SURNAME_AND_PATRONYMIC, name, surname, patronymic));
        List<UserEntity> userEntityList = userService.findByNameAndSurnameAndPatronymic(name, surname, patronymic);
        log.debug(String.format("Пользователи найденные по имени %s, фамилии %s и отчеству %s = %s.",
                name, surname, patronymic, userEntityList));
        return userEntityList.stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList());
    }

    /**
     * метод получения всех имеющихся пользователей
     * @return список пользователей
     */
    @Override
    public List<User> getAll() {
        try {
            List<User> users =
                    userService.findAll().stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList());
            log.debug(String.format("Все имеющиеся пользователи = %s.", users));
            return users;
        } catch (Throwable e) {
            log.error("Ошибка получения списка всех пользователей.");
            log.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    /**
     * метод получения пользователей по списку уникальных идентификаторов
     * @param longList список идентификаторов
     * @return список пользователей
     */
    @Override
    public List<User> getAllById(@NonNull LongListImpl longList) {
        try {
            ObjectUtils.requireNonEmpty(longList);
            log.debug(String.format("Полученный список идентификаторов = %s.", longList));
            List<User> userEntities = userService.findAllById(CommonMapper.INSTANCE.map(longList))
                    .stream().map(UserMapper.INSTANCE::map).collect(Collectors.toList());
            log.debug(String.format("Найденные пользователи = %s.", userEntities));
            return userEntities;
        } catch (Throwable e) {
            log.error(String.format("ошибка получения пользователей по списку идентификаторов = %s.", longList));
            log.error(e.getMessage(), e.getCause());
            return null;
        }

    }

    /**
     * метод удаления пользователя по уникальному идентификатору
     * @param id уникальный идентификатор
     * @return логический ответ
     */
    @Override
    public Boolean deleteById(@NonNull Long id) throws BaseException {
        try {
            ObjectUtils.requireNonEmpty(id);
            log.debug(String.format("Полученный идентификатор %s.", id));
            Boolean deleteCheck = userService.deleteById(id);
            log.debug(String.format(DELETE_RESULT, deleteCheck));
            return deleteCheck;
        } catch (Throwable e) {
            log.error("Удаление пользователя по идентификатору = %s, не удалось.");
            log.error(e.getMessage(), e.getCause());
            return false;
        }
    }

}
