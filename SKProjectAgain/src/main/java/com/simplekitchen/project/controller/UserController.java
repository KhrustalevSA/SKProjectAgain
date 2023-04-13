package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.security.jwt.JwtTokenProvider;
import com.simplekitchen.project.business.service.api.UserControllerService;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.UserRequestInfoImpl;
import com.simplekitchen.project.dto.entity.user.UserResponseInfoImpl;
import com.simplekitchen.project.dto.entity.user.api.UserResponseInfo;
import com.simplekitchen.project.dto.security.AuthenticationRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Map;

/**
 * РЕСТ контроллер для работы с пользователями
 * @author KhrustalevSA
 * @since 26.02.2023
 */
@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController {
    //(need to do) Unit test,

    //TODO(Done) убрать WAR название у проекта в вайлд флае,
    //TODO OpenShift, переписать, убрать Impl'ы: перейти на интерфейсы (JS nodeJS Angular)

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * сервис работы с пользователями
     */
    private final UserControllerService userControllerService;

    /**
     * конструктор с автоопределением бина
     * @param service сервис пользователей
     */
    @Autowired
    public UserController(UserControllerService service, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userControllerService = service;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username  = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            UserEntity user = userControllerService.findByUsername(username);

            if (user ==null) {
                throw new UsernameNotFoundException("");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoleList());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    /**
     * метод сохранения пользователя
     * @param user объект сохраняемого пользователя
     * @return информация о сохраненном пользователе
     * @throws DataBaseException ошибки базы данных
     */
    @PostMapping("/save")
    public UserResponseInfo save(@RequestBody UserImpl user) throws DataBaseException {
        try {
            return userControllerService.save(user);
        } catch (Throwable e) {
            log.error(String.format("Ошибка сохранения пользователя %s", user));
            log.error(e.getMessage(), e.getCause());
            return UserResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(false).description(e.getMessage()).build())
                    .build();
        }
    }

    /**
     * метод получения пользователей по имеющейся информации
     * @param userRequestInfo информация для получения пользователей
     * @return информация о найденных пользователях
     */
    @GetMapping("/get")
    public UserResponseInfo get(@RequestBody UserRequestInfoImpl userRequestInfo) {
        try {
            return UserResponseInfoImpl.builder()
                    .userList(userControllerService.get(userRequestInfo))
                    .status(StatusImpl.builder().success(true).build())
                    .build();
        } catch (Throwable e) {
            log.error(String.format("Ошибка получения пользователя по запросу %s", userRequestInfo));
            log.error(e.getMessage(), e.getCause());
            return UserResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(false).description(e.getMessage()).build())
                    .build();
        }
    }

    /**
     * Метод получения всех имеющихся пользователей
     * @return класс информации со списком всех имеющихся пользователей
     */
    @GetMapping("/get/all")
    public UserResponseInfo getAll() {
        try {
            return UserResponseInfoImpl.builder()
                    .userList(userControllerService.getAll())
                    .status(StatusImpl.builder().success(true).build())
                    .build();
        } catch (Throwable e) {
            log.error("Ошибка получения пользователей", e);
            log.error(e.getMessage(), e.getCause());
            return UserResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(false).description(e.getMessage()).build())
                    .build();
        }

    }

    /**
     * метод получения пользователей по списку идентификаторов
     * @param longList список уникальных идентификаторов
     * @return список найденных пользователей и информация о запросе
     */
    @GetMapping("/get/allByLongList")
    public UserResponseInfo getAllById(@RequestBody LongListImpl longList) {
        try {
            return UserResponseInfoImpl.builder()
                    .userList(userControllerService.getAllById(longList))
                    .status(StatusImpl.builder().success(true).build())
                    .build();
        } catch (Throwable e) {
            log.error("Ошибка получения пользователей", e);
            log.error(e.getMessage(), e.getCause());
            return UserResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(false).description(e.getMessage()).build())
                    .build();
        }

    }

    /**
     * метод удаления пользователя по уникальному идентификатору
     * @param id идентификатор пользователя
     * @return логический ответ
     * @throws BaseException общий класс ошибок
     */
    @PostMapping("/deleteById")
    public Boolean deleteById(@RequestParam Long id) throws BaseException {
        try {
            return userControllerService.deleteById(id);
        } catch (Throwable e) {
            log.error("Ошибка удаления пользователя по идентификатору", e);
            log.error(e.getMessage(), e.getCause());
            return false;
        }
    }

    /**
     * метод удаления пользователей по списку идентификаторов
     * @param longList список идентификаторов пользователей
     * @return логический ответ
     */
    @PostMapping("/deleteByIdList")
    public Boolean deleteByIdList(@RequestBody LongListImpl longList) {
        try {
            if (longList == null || longList.getLongList().isEmpty()) {
                throw new ValidationException("Передан пустой список целых чисел.");
            }
            boolean deleteCheck;
            for (Long id : longList.getLongList()) {
                deleteCheck = userControllerService.deleteById(id);
                if (!deleteCheck) {
                    return false;
                }
            }
            return true;
        } catch (Throwable e) {
            log.error("Ошибка удаления пользователей по идентификаторам", e);
            log.error(e.getMessage(), e.getCause());
            return false;
        }
    }
}

