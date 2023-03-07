package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.dto.entity.user.api.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * класс валидатора для пользователя
 * @author KhrustalevSA
 * @since 07.03.2023
 */
@Component
@Qualifier(value = "userValidatorImpl")
public class UserValidatorImpl implements UserValidator<User>{
    @Override
    public void validate(User user) throws ValidationException {
        boolean valid = user != null;
        if (valid) {
            valid = user.getName() != null && user.getSurname() != null;
        }
        if (!valid) {
            throw new ValidationException("Не все обязательные поля заполнены");
        }
    }
}
