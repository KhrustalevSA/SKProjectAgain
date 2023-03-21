package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.dto.entity.user.UserRequestInfoImpl;
import org.junit.Assert;
import org.junit.Test;

public class UserInfoRequestValidatorTest {

    UserInfoRequestValidator validator = new UserInfoRequestValidator();

    @Test
    public void validateForAllSuccess() {
        UserRequestInfoImpl requestInfo = UserRequestInfoImpl.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .patronymic("Patronymic")
                .build();

        try {
            validator.validate(requestInfo);
        } catch (ValidationException e) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void validateForIdSuccess() {
        UserRequestInfoImpl requestInfo = UserRequestInfoImpl.builder()
                .id(1L)
                .build();

        try {
            validator.validate(requestInfo);
        } catch (ValidationException e) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void validateForIdAndNameSuccess() {
        UserRequestInfoImpl requestInfo = UserRequestInfoImpl.builder()
                .id(1L)
                .name("Name")
                .build();

        try {
            validator.validate(requestInfo);
        } catch (ValidationException e) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void validateForIdAndSurnameSuccess() {
        UserRequestInfoImpl requestInfo = UserRequestInfoImpl.builder()
                .id(1L)
                .name("Surname")
                .build();

        try {
            validator.validate(requestInfo);
        } catch (ValidationException e) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void validateForIdAndPatronymicSuccess() {
        UserRequestInfoImpl requestInfo = UserRequestInfoImpl.builder()
                .id(1L)
                .patronymic("Patronymic")
                .build();

        try {
            validator.validate(requestInfo);
        } catch (ValidationException e) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void validateForNameAndSurnameSuccess() {
        UserRequestInfoImpl requestInfo = UserRequestInfoImpl.builder()
                .name("Name")
                .surname("Surname")
                .build();

        try {
            validator.validate(requestInfo);
        } catch (ValidationException e) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void validateFail() {
        Assert.assertThrows(ValidationException.class, () -> validator.validate(UserRequestInfoImpl.builder().build()));
        Assert.assertThrows(
                ValidationException.class,
                () -> validator.validate(UserRequestInfoImpl.builder().name("Name").build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> validator.validate(UserRequestInfoImpl.builder().surname("Surname").build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> validator.validate(UserRequestInfoImpl.builder().name("name").patronymic("P").build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> validator.validate(UserRequestInfoImpl.builder().surname("S").patronymic("P").build())
        );
    }
}