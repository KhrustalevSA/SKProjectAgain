package com.simplekitchen.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.service.UserControllerServiceImpl;
import com.simplekitchen.project.business.service.api.UserControllerService;
import com.simplekitchen.project.business.utils.UserInfoRequestValidator;
import com.simplekitchen.project.business.utils.UserSaveValidator;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.UserService;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.UserRequestInfoImpl;
import com.simplekitchen.project.dto.entity.user.api.UserResponseInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.*;

public class UserControllerTest {

    private final UserService service = Mockito.mock(UserService.class);

    private final UserControllerService controllerService = new UserControllerServiceImpl(
            service,
            new UserInfoRequestValidator(),
            new UserSaveValidator()
    );

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final File jsonFileWithUser =
            new File("src/test/resources/static/Json/user/User.json");
    private final File jsonFileWithUserWithNameAndPatronymicOnly =
            new File("src/test/resources/static/Json/user/UserWithNameAndPatronymic.json");
    private final File jsonFileWithUserWithNameOnly =
            new File("src/test/resources/static/Json/user/UserWithNameOnly.json");
    private final File jsonFileWithUserWithNameAndSurnameAndPatronymicOnly =
            new File("src/test/resources/static/Json/user/UserWithNameSurnameAndPatronymicOnly.json");
    private final File jsonFileWithUserWithSurnameOnly =
            new File("src/test/resources/static/Json/user/UserWithSurnameOnly.json");
    private final File jsonFileWithUserRequest =
            new File("src/test/resources/static/Json/user/UserRequestInfo.json");
    private final File jsonFileWithUserRequestWithCookingTimeOnly =
            new File("src/test/resources/static/Json/user/UserRequestInfoWithCookingTimeOnly.json");
    private final File jsonFileWithUserRequestWithNameAndPatronymicOnly =
            new File("src/test/resources/static/Json/user/UserRequestInfoWithNameAndPatronymicOnly.json");
    private final File jsonFileWithUserRequestWithNameOnly =
            new File("src/test/resources/static/Json/user/UserRequestInfoWithNameOnly.json");
    private final File jsonFileWithUserRequestWithSurnameOnly =
            new File("src/test/resources/static/Json/user/UserRequestInfoWithSurnameOnly.json");
    private final File jsonFileWithLongList =
            new File("src/test/resources/static/Json/common/LongList.json");
    private final File jsonFileWithLongListWithEmptyList =
            new File("src/test/resources/static/Json/common/LongListWithEmptyList.json");
    private final File jsonFileWithLongListEmpty =
            new File("src/test/resources/static/Json/common/LongListEmpty.json");
    private final UserController controller = new UserController(controllerService);

    @Before
    public void setUp() throws Throwable {
        Mockito.when(service.save(Mockito.any())).thenReturn(UserEntityImpl.builder()
                .name("Name")
                .surname("Surname")
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
        try {
            UserImpl user = objectMapper.readValue(jsonFileWithUser, UserImpl.class);
            savedUser = controller.save(user);
            Assert.assertEquals(user, savedUser.getUserList().get(0));
        } catch (Throwable e) {
            Assert.fail();
        }

    }

    @Test
    public void saveFail() {
        try {
            Mockito.when(service.save(Mockito.any())).thenThrow(DataBaseException.class);
            assertFalse(controller.save(objectMapper.readValue(
                    jsonFileWithUserWithNameOnly,
                    UserImpl.class)
            ).getStatus().isSuccess());
            assertFalse(controller.save(objectMapper.readValue(
                    jsonFileWithUserWithSurnameOnly,
                    UserImpl.class)
            ).getStatus().isSuccess());
            assertFalse(controller.save(objectMapper.readValue(
                    jsonFileWithUserWithNameAndPatronymicOnly,
                    UserImpl.class)
            ).getStatus().isSuccess());
            assertFalse(controller.save(objectMapper.readValue(
                    jsonFileWithUserWithNameAndSurnameAndPatronymicOnly,
                    UserImpl.class)
            ).getStatus().isSuccess());
            assertFalse(controller.save(objectMapper.readValue(
                    jsonFileWithUser,
                    UserImpl.class)
            ).getStatus().isSuccess());
        } catch (Throwable e) {
            Assert.fail();
        }
    }

    @Test
    public void getSuccess() {
        UserResponseInfo userResponseInfo = null;

        try {
            UserRequestInfoImpl requestInfo = objectMapper.readValue(jsonFileWithUserRequest, UserRequestInfoImpl.class);
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

            assertFalse(controller.get(objectMapper.readValue(
                    jsonFileWithUserRequestWithNameOnly,
                    UserRequestInfoImpl.class)
            ).getStatus().isSuccess());
            assertFalse(controller.get(objectMapper.readValue(
                    jsonFileWithUserRequestWithSurnameOnly,
                    UserRequestInfoImpl.class)
            ).getStatus().isSuccess());
            assertFalse(controller.get(objectMapper.readValue(
                    jsonFileWithUserRequestWithNameAndPatronymicOnly,
                    UserRequestInfoImpl.class)
            ).getStatus().isSuccess());
            assertFalse(controller.get(objectMapper.readValue(
                    jsonFileWithUserRequestWithCookingTimeOnly,
                    UserRequestInfoImpl.class)
            ).getStatus().isSuccess());
            assertFalse(controller.get(objectMapper.readValue(
                    jsonFileWithUserRequest,
                    UserRequestInfoImpl.class)
            ).getStatus().isSuccess());
        } catch (Throwable e) {
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
        com.simplekitchen.project.dto.common.LongListImpl longList = null;
        try {
            longList = objectMapper.readValue(jsonFileWithLongList, LongListImpl.class);
        } catch (Throwable e ) {
            Assert.fail();
        }
        responseInfo = controller.getAllById(longList);

        Assert.assertEquals(longList.getLongList().size(), responseInfo.getUserList().size());
    }

    @Test
    public void getAllByIdFail() {
        try {
            Mockito.when(service.findAllById(Mockito.any())).thenThrow(DataBaseException.class);
            Assert.assertEquals(
                    0,
                    controller.getAllById(objectMapper.readValue(jsonFileWithLongList, LongListImpl.class))
                            .getUserList().size()
            );
            Assert.assertEquals(
                    0,
                    controller.getAllById(objectMapper.readValue(jsonFileWithLongListEmpty, LongListImpl.class))
                            .getUserList().size()
            );
            Assert.assertEquals(
                    0,
                    controller.getAllById(objectMapper.readValue(jsonFileWithLongListWithEmptyList, LongListImpl.class))
                            .getUserList().size()
            );
        } catch (IOException | DataBaseException e) {
            Assert.fail();
        }
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
        LongListImpl longList = null;
        try {
            longList = objectMapper.readValue(jsonFileWithLongList, LongListImpl.class);
        } catch (IOException e) {
            Assert.fail();
        }
        Assert.assertTrue(controller.deleteByIdList(longList));
    }

    @Test
    public void deleteByIdListFail() {
        LongListImpl longListEmpty = null;
        LongListImpl longListWithEmptyList = null;
        try {
            longListEmpty = objectMapper.readValue(jsonFileWithLongListEmpty, LongListImpl.class);
            longListWithEmptyList = objectMapper.readValue(jsonFileWithLongListWithEmptyList, LongListImpl.class);
            Mockito.when(service.deleteById(Mockito.anyLong())).thenThrow(DataBaseException.class);
        } catch (Throwable e) {
            Assert.fail();
        }
        Assert.assertEquals(
                false,
                controller.deleteByIdList(longListEmpty)
        );
        Assert.assertEquals(
                false,
                controller.deleteByIdList(longListWithEmptyList)
        );
    }
}