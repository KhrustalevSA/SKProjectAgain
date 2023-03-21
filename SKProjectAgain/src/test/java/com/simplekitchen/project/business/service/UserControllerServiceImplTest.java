package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.service.api.UserControllerService;
import com.simplekitchen.project.business.utils.UserInfoRequestValidator;
import com.simplekitchen.project.business.utils.UserSaveValidator;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.UserService;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.UserRequestInfoImpl;
import com.simplekitchen.project.dto.entity.user.UserResponseInfoImpl;
import com.simplekitchen.project.dto.entity.user.api.User;
import com.simplekitchen.project.dto.entity.user.api.UserResponseInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

public class UserControllerServiceImplTest {

    private final UserService serviceDao = Mockito.mock(UserService.class);

    private final UserControllerService userService = new UserControllerServiceImpl(
            serviceDao,
            new UserInfoRequestValidator(),
            new UserSaveValidator()
    );

    @Before
    public void setUp() throws DataBaseException {
        Mockito.when(serviceDao.findById(Mockito.anyLong())).thenReturn(UserEntityImpl.builder().build());
        Mockito.when(serviceDao.findByNameAndSurnameAndPatronymic(Mockito.anyString(), Mockito.anyString(), Mockito.any()))
                .thenReturn(Collections.singletonList(UserEntityImpl.builder().build()));
        Mockito.when(serviceDao.findAll()).thenReturn(Collections.emptyList());
        Mockito.when(serviceDao.deleteById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(serviceDao.save(Mockito.any())
                    ).thenReturn(UserEntityImpl.builder().name("Name").surname("Surname").build());
        Mockito.when(serviceDao.findAll()).thenReturn(Collections.singletonList(UserEntityImpl.builder().build()));
        Mockito.when(serviceDao.findAllById(Mockito.any())
                    ).thenReturn(Collections.singletonList(UserEntityImpl.builder().build()));
        Mockito.when(serviceDao.deleteById(Mockito.anyLong())).thenReturn(true);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(serviceDao);
    }

    @Test
    public void getByIdSuccess() {
        UserRequestInfoImpl userRequestInfo = UserRequestInfoImpl.builder().id(1L).build();
        List<User> users = null;
        try {
            users = userService.get(userRequestInfo);
        } catch (Throwable e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(CollectionUtils.isNotEmpty(users));
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void getByFioSuccess() {
        UserRequestInfoImpl userRequestInfo = UserRequestInfoImpl.builder().name("Ivan").surname("Ivanov").build();
        List<User> users = null;

        try {
            users = userService.get(userRequestInfo);
        } catch (Throwable e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertTrue(CollectionUtils.isNotEmpty(users));
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void getValidationRequestFail() {
        Assert.assertThrows(
                ValidationException.class,
                () -> userService.get(UserRequestInfoImpl.builder().name("Ivan").build())
        );
        Assert.assertThrows(ValidationException.class, () -> userService.get(UserRequestInfoImpl.builder().build()));
        Assert.assertThrows(ValidationException.class, () -> userService.get(null));
        Assert.assertThrows(
                ValidationException.class,
                () -> userService.get(UserRequestInfoImpl.builder().surname("Ivanov").patronymic("Ivanovich").build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> userService.get(UserRequestInfoImpl.builder().patronymic("Ivanovich").build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> userService.get(UserRequestInfoImpl.builder().build())
                );
    }

    @Test
    public void getAllSuccess() {
        List<User> userList = new ArrayList<>();
        try {
            userList = userService.getAll();
        } catch (Throwable e) {
            Assert.fail();
        }
        Assert.assertEquals(1, userList.size());
    }

    @Test
    public void getAllFail() {
        List<User> userList = new ArrayList<>();
        try {
            Mockito.when(serviceDao.findAll()).thenThrow(new DataBaseException(""));
            userList = userService.getAll();
        } catch (Throwable throwable) {
            Assert.fail();
        }
        Assert.assertSame(Collections.emptyList(), userList);
    }

    @Test
    public void saveUserSuccess() {
        UserImpl user = UserImpl.builder().name("Name").surname("Surname").build();
        UserResponseInfo responseInfo = UserResponseInfoImpl.builder().build();
        try {
            responseInfo = userService.save(user);
        } catch (Throwable e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(1, responseInfo.getUserList().size());
    }

    @Test
    public void saveUserFail() {
        UserImpl user = UserImpl.builder().surname("Surname").build();
        try {
            userService.save(user);
            Assert.fail();
        } catch (Throwable e) {
            Assert.assertEquals(ValidationException.class, e.getClass());
        }
    }

    @Test
    public void getValidationUserSaveFail() {
        Assert.assertThrows(ValidationException.class, () -> userService.save(UserImpl.builder().build()));
        Assert.assertThrows(ValidationException.class, () -> userService.save(UserImpl.builder().name("Ivan").build()));
        Assert.assertThrows(ValidationException.class, () -> userService.save(UserImpl.builder().patronymic("Ivanovich").name("Ivan").build()));
        Assert.assertThrows(ValidationException.class, () -> userService.save(UserImpl.builder().name("Ivan").sex("M").build()));
        Assert.assertThrows(
                ValidationException.class,
                () -> userService.save(UserImpl.builder()
                    .name("Ivan")
                    .birthDate(new GregorianCalendar(2001, Calendar.JULY,1))
                    .build())
        );
    }

    @Test
    public void getAllByIdSuccess() {
        LongListImpl longList = LongListImpl.builder().longList(Collections.singletonList(1L)).build();
        List<User> userList;

        userList = userService.getAllById(longList);

        Assert.assertEquals(1, userList.size());
    }

    @Test
    public void getAllByIdFail() {
        LongListImpl longList = LongListImpl.builder().build();
        List<User> userList;

        userList = userService.getAllById(longList);

        Assert.assertEquals(Collections.emptyList(), userList);
    }

    @Test
    public void deleteByIdSuccess() {
        Boolean deleteCheck;

        deleteCheck = userService.deleteById(1L);

        Assert.assertEquals(true, deleteCheck);
    }

    @Test
    public void deleteByIdFail() {
        Boolean deleteCheck;
        Long value = null;

        deleteCheck = userService.deleteById(value);

        Assert.assertEquals(false, deleteCheck);
    }

}