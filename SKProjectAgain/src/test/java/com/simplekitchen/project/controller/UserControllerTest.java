package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.service.UserControllerServiceImpl;
import com.simplekitchen.project.business.service.api.UserControllerService;
import com.simplekitchen.project.business.utils.UserInfoRequestValidator;
import com.simplekitchen.project.business.utils.UserSaveValidator;
import com.simplekitchen.project.business.utils.api.RequestValidator;
import com.simplekitchen.project.dao.entity.city.CityEntityImpl;
import com.simplekitchen.project.dao.entity.city.CityNameEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.UserService;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.city.CityImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.UserRequestInfoImpl;
import com.simplekitchen.project.dto.entity.user.UserResponseInfoImpl;
import com.simplekitchen.project.dto.entity.user.api.UserResponseInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class UserControllerTest {

    private final UserService service = Mockito.mock(UserService.class);

    private final UserControllerService controllerService = new UserControllerServiceImpl(
            service,
            new UserInfoRequestValidator(),
            new UserSaveValidator()
    );

    private final UserController controller = new UserController(controllerService);

    @Before
    public void setUp() throws Throwable {
        Mockito.when(service.save(Mockito.any())).thenReturn(UserEntityImpl.builder()
                .id(1L)
                .name("N")
                .surname("S")
                .patronymic("P")
                .sex("M")
                .birthDate(new GregorianCalendar(2000, Calendar.FEBRUARY,1))
                .city(CityEntityImpl.builder().cityName(CityNameEntityImpl.builder().cityName("N").build()).build())
                .favoriteRecipeList(Collections.singletonList(RecipeEntityImpl.builder().name("N").build()))
                .build());
        Mockito.when(service.findByNameAndSurnameAndPatronymic(
                        Mockito.anyString(),
                        Mockito.anyString(),
                        Mockito.any()))
                .thenReturn(Collections.singletonList(UserEntityImpl.builder().build()));
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(UserEntityImpl.builder().build());
        Mockito.when(service.findAll()).thenReturn(Collections.singletonList(UserEntityImpl.builder().build()));
        Mockito.when(service.findAllById(Mockito.any()))
                .thenReturn(Collections.singletonList(UserEntityImpl.builder().build()));
        Mockito.when(service.deleteById(Mockito.anyLong())).thenReturn(true);

    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(service);
    }

    @Test
    public void saveSuccess() {
        UserResponseInfo savedUser;
        UserImpl user = UserImpl.builder()
                .id(1L)
                .name("N")
                .surname("S")
                .patronymic("P")
                .sex("M")
                .birthDate(new GregorianCalendar(2000, Calendar.FEBRUARY,1))
                .city(CityImpl.builder().cityName("N").build())
                .favoriteRecipeList(Collections.singletonList(RecipeImpl.builder().name("N").build()))
                .build();
        try {
            savedUser = controller.save(user);
            Assert.assertEquals(user.getId(), savedUser.getUserList().get(0).getId());
            Assert.assertEquals(user.getName(), savedUser.getUserList().get(0).getName());
            Assert.assertEquals(user.getSex(), savedUser.getUserList().get(0).getSex());
            Assert.assertEquals(user.getPatronymic(), savedUser.getUserList().get(0).getPatronymic());
            Assert.assertEquals(user.getSex(), savedUser.getUserList().get(0).getSex());
            Assert.assertEquals(user.getBirthDate(), savedUser.getUserList().get(0).getBirthDate());
            Assert.assertEquals(user.getCity().getCityName(), savedUser.getUserList().get(0).getCity().getCityName());
            Assert.assertEquals(
                    user.getFavoriteRecipeList().get(0).getName(),
                    savedUser.getUserList().get(0).getFavoriteRecipeList().get(0).getName()
            );
        } catch (DataBaseException e) {
            Assert.fail();
        }

    }

    @Test
    public void saveFail() {
        try {
            Mockito.when(service.save(Mockito.any())).thenThrow(DataBaseException.class);
            assertFalse(controller.save(UserImpl.builder().name("N").build()).getStatus().isSuccess());
            assertFalse(controller.save(UserImpl.builder().surname("S").build()).getStatus().isSuccess());
            assertFalse(controller.save(UserImpl.builder().id(1L).build()).getStatus().isSuccess());
            assertFalse(controller.save(UserImpl.builder().name("N").patronymic("P").build()).getStatus().isSuccess());
            assertFalse(controller.save(UserImpl.builder().name("N").patronymic("P").build()).getStatus().isSuccess());
        } catch (DataBaseException e) {
            Assert.fail();
        }
    }

    @Test
    public void getSuccess() {
        UserResponseInfo userResponseInfo = null;
        UserRequestInfoImpl requestInfo = UserRequestInfoImpl.builder().name("N").surname("N").build();

        try {
             userResponseInfo = controller.get(requestInfo);
        } catch (Throwable e) {
            Assert.fail();
        }

        Assert.assertEquals(1, userResponseInfo.getUserList().size());
    }

    @Test
    public void getFail() {
        try {
            Mockito.when(service.findById(Mockito.anyLong())).thenThrow(DataBaseException.class);
            Mockito.when(service.findByNameAndSurnameAndPatronymic(Mockito.any(), Mockito.any(), Mockito.any()))
                    .thenThrow(DataBaseException.class);
            assertFalse(controller.get(UserRequestInfoImpl.builder().name("N").build()).getStatus().isSuccess());
            assertFalse(controller.get(UserRequestInfoImpl.builder().surname("S").build()).getStatus().isSuccess());
            assertFalse(controller.get(UserRequestInfoImpl.builder().name("N").patronymic("P").build())
                    .getStatus().isSuccess());
            assertFalse(controller.get(UserRequestInfoImpl.builder().id(1L).build()).getStatus().isSuccess());
            assertFalse(controller.get(UserRequestInfoImpl.builder().name("N").surname("S").build())
                    .getStatus().isSuccess());
        } catch (DataBaseException e) {
            Assert.fail();
        }
    }

    @Test
    public void getAllSuccess() {
        UserResponseInfo responseInfo;

        responseInfo = controller.getAll();

        assertTrue(responseInfo.getStatus().isSuccess());
    }

    @Test
    public void getAllFail() {
        try {
            Mockito.when(service.findAll()).thenThrow(DataBaseException.class);
        } catch (DataBaseException e) {
            Assert.fail();
        }

        Assert.assertEquals(Collections.emptyList(), controller.getAll().getUserList());
    }

    @Test
    public void getAllByIdSuccess() {
        UserResponseInfo responseInfo;
        com.simplekitchen.project.dto.common.LongListImpl longList = com.simplekitchen.project.dto.common.LongListImpl.builder()
                .longList(Collections.singletonList(1L))
                .build();
        responseInfo = controller.getAllById(longList);

        Assert.assertEquals(longList.getLongList().size(), responseInfo.getUserList().size());
    }

    @Test
    public void getAllByIdFail() {
        Assert.assertEquals(0, controller.getAllById(LongListImpl.builder().build()).getUserList().size());
    }

    @Test
    public void deleteByIdSuccess() {
        Boolean deleteCheck = null;

        try {
            deleteCheck = controller.deleteById(1L);
        } catch (BaseException e) {
            Assert.fail();
        }

        Assert.assertTrue(deleteCheck);
    }

    @Test
    public void deleteByIdFail() {
        try {
            Mockito.when(service.deleteById(Mockito.anyLong())).thenThrow(DataBaseException.class);
            Assert.assertFalse(controller.deleteById(1L));
        } catch (Throwable e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteByIdListSuccess() {
        Boolean deleteCheck;
        deleteCheck = controller.deleteByIdList(LongListImpl.builder().longList(Collections.singletonList(1L)).build());
        Assert.assertTrue(deleteCheck);
    }

    @Test
    public void deleteByIdListFail() {
        try {
            Mockito.when(service.deleteById(Mockito.anyLong())).thenThrow(DataBaseException.class);
            Assert.assertFalse(
                    controller.deleteByIdList(LongListImpl.builder().longList(Collections.singletonList(1L)).build())
            );
        } catch (Throwable e) {
            Assert.fail();
        }
    }
}