package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.utils.api.RequestValidator;
import com.simplekitchen.project.dto.entity.user.api.UserRequestInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * класс валидатора для запроса пользователя
 * @author KhrustalevSA
 * @since 07.03.2023
 */
@Component
@Qualifier(value = "userRequestInfoValidator")
public class UserInfoRequestValidator implements RequestValidator<UserRequestInfo> {

    /**
     * метод валидации информации о запрошенном пользователе
     * @param request запрос с информацией о пользователе
     * @throws ValidationException ошибка валидации
     */
    @Override
    public void validate(UserRequestInfo request) throws ValidationException {
        boolean valid = request != null;
        if (valid) {
            valid = request.getId() != null ||
                    (StringUtils.isNotBlank(request.getName()) && StringUtils.isNotBlank(request.getSurname()));
        }
        if (!valid) {
            throw new ValidationException("Не все обязательные поля заполнены");
        }
    }
}