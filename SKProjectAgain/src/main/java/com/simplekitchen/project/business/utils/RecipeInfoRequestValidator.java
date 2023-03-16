package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.utils.api.RequestValidator;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeRequestInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * класс валидатора для запроса с информацией о рецепте
 * @author KhrustalevSA
 * @since 16.03.2023
 */
@Component
@Qualifier("recipeInfoRequestValidator")
public class RecipeInfoRequestValidator implements RequestValidator<RecipeRequestInfo> {

    /**
     * метод валидации запроса
     * @param request запрос
     * @throws ValidationException ошибка валидации
     */
    @Override
    public void validate(RecipeRequestInfo request) throws ValidationException {
        boolean valid = request != null;
        if (valid) {
            valid = request.getId() != null || request.getName() != null;
        }
        if (!valid) {
            throw new ValidationException("Не все обязательные поля заполнены");
        }
    }
}
