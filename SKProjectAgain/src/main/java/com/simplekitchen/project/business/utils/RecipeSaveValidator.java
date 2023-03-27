package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.utils.api.ObjectSaveValidator;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeListRequestInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * класс валидатора сохранения рецепта
 * @author KhrustalevSA
 * @since 16.03.2023
 */
@Component
@Qualifier("recipeSaveValidator")
public class RecipeSaveValidator implements ObjectSaveValidator<RecipeListRequestInfo> {

    /**
     * метод валидации объекта сохранения
     * @param savedObject объект для сохранения
     * @throws ValidationException ошибка валидации
     */
    @Override
    public void validate(RecipeListRequestInfo savedObject) throws ValidationException {
        boolean valid = savedObject != null &&  savedObject.getRecipeList().size() > 0 && savedObject.getRecipeList() != null ;
        if (valid) {
            List<RecipeImpl> recipeList = savedObject.getRecipeList();
            for (Recipe recipe : recipeList) {
                valid = StringUtils.isNotBlank(recipe.getName())
                       // && recipe.getIngredientsList() != null
                        && StringUtils.isNotBlank(recipe.getDifficulty());            }
        }
        if (!valid) {
            throw new ValidationException("Не все обязательные поля заполнены");
        }
    }

}
