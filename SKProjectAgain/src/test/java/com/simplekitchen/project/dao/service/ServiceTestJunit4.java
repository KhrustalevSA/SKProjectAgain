package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.service.UserControllerServiceImpl;
import com.simplekitchen.project.business.service.api.UserControllerService;
import com.simplekitchen.project.business.utils.UserSaveValidator;
import com.simplekitchen.project.business.utils.UserInfoRequestValidator;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.UserService;
import com.simplekitchen.project.dto.entity.user.UserRequestInfoImpl;
import com.simplekitchen.project.dto.entity.user.api.User;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class ServiceTestJunit4 {

//    private static final String db_driver = "org.h2.Driver";
//    private static final Service<RecipeEntityImpl> recipeService = new RecipeServiceImpl();
//    private static Service<IngredientEntityImpl> ingredientService = new IngredientServiceImpl();
//    private static UserRepository repository;

    private final UserService serviceDao = Mockito.mock(UserService.class);

    private final UserControllerService userService = new UserControllerServiceImpl(
            serviceDao,
            new UserInfoRequestValidator(),
            new UserSaveValidator());

    @Before
    public void setUp() throws DataBaseException {
        Mockito.when(serviceDao.findById(Mockito.anyLong())).thenReturn(UserEntityImpl.builder().build());
        Mockito.when(serviceDao.findByNameAndSurnameAndPatronymic(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.singletonList(UserEntityImpl.builder().build()));
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
    public void getValidationFail() {
        UserRequestInfoImpl userRequestInfo = UserRequestInfoImpl.builder().name("Ivan").build();
        Assert.assertThrows(ValidationException.class, () -> userService.get(UserRequestInfoImpl.builder().name("Ivan").build()));
        Assert.assertThrows(ValidationException.class, () -> userService.get(UserRequestInfoImpl.builder().build()));
        Assert.assertThrows(ValidationException.class, () -> userService.get(null));
        Assert.assertThrows(ValidationException.class, () -> userService.get(UserRequestInfoImpl.builder()
                .surname("Ivanov").patronymic("Ivanovich").build()));
        Assert.assertThrows(
                ValidationException.class,
                () -> userService.get(UserRequestInfoImpl.builder().patronymic("Ivanovich").build())
        );
    }

    @After
    public void reset() {
        Mockito.reset(serviceDao);
    }

}