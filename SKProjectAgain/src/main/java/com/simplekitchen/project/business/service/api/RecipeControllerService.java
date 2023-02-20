package com.simplekitchen.project.business.service.api;

import com.simplekitchen.project.business.entity.common.api.LongList;
import com.simplekitchen.project.business.entity.recipe.api.RecipeRequestInfo;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.exception.GetException;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;


public interface RecipeControllerService {

    Recipe save(RecipeImpl recipe) throws DataBaseException, BaseException;

    /**
     * метод сохранения всех пользователей, возвращает список сохраненных пользователей
     * @param userList
     * @return List<UserEntityImpl>
     */
    RecipeList saveAll(RecipeListImpl recipeList) throws BaseException, DataBaseException;

    /**
     * метод получения пользователя по уникальному идентификатору
     * @param id
     * @return Optional<UserEntityImpl>
     */
    Recipe get(Long id) throws DataBaseException, BaseException;

    /**
     * метод получения пользователя по классу информации
     * @param userInfo
     * @return Optional<UserEntityImpl>
     */
    RecipeList get(RecipeRequestInfo userInfo) throws BaseException;

    /**
     * метод получения всех существующих пользователей
     * @return List<UserEntityImpl>
     */
    RecipeList getAll();

    /**
     * метод для получения списка пользователей по их уникальным идентификаторам
     * @param longList
     * @return List<UserEntityImpl>
     */
    RecipeList getAllById(LongList longList);

    /**
     * удалить пользователя по его уникальному идентификатору
     * @param id
     * @return Boolean
     */
    Boolean deleteById(Long id) throws BaseException;

    Boolean deleteByName(RecipeRequestInfo recipeRequestInfo);




}
