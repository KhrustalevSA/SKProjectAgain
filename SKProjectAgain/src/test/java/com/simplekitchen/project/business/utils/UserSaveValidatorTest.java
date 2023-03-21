package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.UserRequestInfoImpl;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserSaveValidatorTest {

    UserSaveValidator validator = new UserSaveValidator();

    @Test
    public void validateForAllNeededSuccess() {
        UserImpl user = UserImpl.builder()
                .name("Name")
                .surname("Surname")
                .patronymic("Patronymic")
                .build();

        try {
            validator.validate(user);
        } catch (ValidationException e) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void validateForNameAndSurnameSuccess() {
        UserImpl user = UserImpl.builder()
                .name("Name")
                .surname("Surname")
                .build();

        try {
            validator.validate(user);
        } catch (ValidationException e) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void validateFail() {
        Assert.assertThrows(ValidationException.class, () -> validator.validate(null));
        Assert.assertThrows(ValidationException.class, () -> validator.validate(UserImpl.builder().build()));
        Assert.assertThrows(
                ValidationException.class,
                () -> validator.validate(UserImpl.builder().name("Name").build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> validator.validate(UserImpl.builder().surname("Surname").build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> validator.validate(UserImpl.builder().name("name").patronymic("P").build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> validator.validate(UserImpl.builder().surname("S").patronymic("P").build())
        );
    }
}