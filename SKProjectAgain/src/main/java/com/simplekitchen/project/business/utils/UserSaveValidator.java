package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.utils.api.ObjectSaveValidator;
import com.simplekitchen.project.dto.entity.user.api.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * класс валидатора для пользователя
 * @author KhrustalevSA
 * @since 07.03.2023
 */
@Component
@Qualifier(value = "userValidatorImpl")
public class UserSaveValidator implements ObjectSaveValidator<User> {

    /**
     * метод валидации сохраняемого пользователя
     * @param savedObject объект для сохранения
     * @throws ValidationException ошибка валидации
     */
    @Override
    public void validate(User savedObject) throws ValidationException {
        boolean valid = savedObject != null;
        if (valid) {
            valid = StringUtils.isNotBlank(savedObject.getName()) && StringUtils.isNotBlank(savedObject.getSurname());
        }
        if (!valid) {
            throw new ValidationException("Не все обязательные поля заполнены");
        }
    }
}
