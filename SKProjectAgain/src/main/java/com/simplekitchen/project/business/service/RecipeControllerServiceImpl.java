package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.entity.common.api.LongList;
import com.simplekitchen.project.business.entity.recipe.api.RecipeRequestInfo;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.mapper.recipe.RecipeMapper;
import com.simplekitchen.project.business.service.api.RecipeControllerService;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.RecipeService;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImplListImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * класс севриса контроллера рецептов
 * @since 21.02.2023
 * @author KhrustalevSA
 */
@Slf4j
@Service
@NoArgsConstructor
public class RecipeControllerServiceImpl implements RecipeControllerService {

    private static final String RECEIVED_ID = "Полученный идентификаор %s.";
    private static final String RECEIVED_ID_LIST = "Полученный список идентификаторов %s.";
    private static final String DELETE_RESULT = "Результат удаления: %b";

    /**
     * объект сервиса рецептов
     */
    private static RecipeService recipeService;

    /**
     * конструктор севриса контроллера рецептов
     * @param recipeServiceDao дао сервис рецептов
     */
    @Autowired
    public RecipeControllerServiceImpl(RecipeService recipeServiceDao) {
        recipeService = recipeServiceDao;
    }

    /**
     * метод сохранения рецепта
     * @param recipe объект рецепта для сохранения
     * @return объект сохраненного рецепта
     * @throws DataBaseException ошибка базы данных
     * @throws BaseException общий класс ошибок сервиса
     */
    @Override
    public Recipe save(RecipeImpl recipe) throws DataBaseException, BaseException {
        try {
            validate(recipe);
            log.debug("Полученный рецепт для сохранения" + recipe);
            RecipeEntity savedRecipeEntity = recipeService.save(RecipeMapper.INSTANCE.map(recipe));
            log.debug(String.format("Сохраненный рецепт = %s", savedRecipeEntity));
            return  RecipeMapper.INSTANCE.map(savedRecipeEntity);
        } catch (Throwable e) {
            log.error(String.format("Не удалось сохранить рецепт %s", recipe));
            log.error(e.getMessage(),e.getCause());
            return RecipeImpl.builder().build();
        }
    }

    /**
     * метод сохранения списка рецептов
     * @param recipeList список рецептов для сохранения
     * @return список сохраненных рецептов
     * @throws DataBaseException ошибка базы данных
     * @throws BaseException общий класс ошибок сервиса
     */
    @Override
    public RecipeList saveAll(RecipeImplListImpl recipeList) throws BaseException, DataBaseException {
        try {
            validate(recipeList);
            log.debug(String.format("Запрошенный список рецептов = %s.", recipeList));
            com.simplekitchen.project.dao.entity.recipe.api.RecipeList recipeEntityList =
                    recipeService.saveAll(RecipeMapper.INSTANCE.map(recipeList));
            log.debug(String.format("Сохраненный список рецептов = %s.",recipeEntityList));
            return RecipeMapper.INSTANCE.map(recipeEntityList);
        } catch (Throwable e) {
            log.error(String.format("Не удалось сохранить список рецептов %s.", recipeList));
            log.error(e.getMessage(),e.getCause());
            return RecipeListImpl.builder().build();
        }
    }

    /**
     * метод получения рецепта по уникальному идентификатору
     * @param id уникальный идентификатор рецепта
     * @return наденный рецепт
     * @throws DataBaseException ошибка базы данных
     * @throws BaseException общий класс ошибок сервиса
     */
    @Override
    public Recipe get(Long id) throws DataBaseException, BaseException {
        try {
            validate(id);
            log.debug(String.format(RECEIVED_ID,id));
            Recipe foundRecipe = RecipeMapper.INSTANCE.map(recipeService.findById(id));
            log.debug(String.format("Реуепт найденный по идентификатору %s = %s", id, foundRecipe));
            return foundRecipe;
        } catch (Throwable e) {
            log.error(String.format("Не удалось найти рецепт по идентификатору %d",id));
            log.error(e.getMessage(),e.getCause());
            return RecipeImpl.builder().build();
        }
    }

    /**
     * метод получения списка рецептов по имеющийся информации
     * @param recipeRequestInfo объект содержащий информацию для поиска
     * @return список найденных рецептов
     * @throws BaseException общий класс ошибок сервиса
     */
    @Override
    public RecipeList get(RecipeRequestInfo recipeRequestInfo) throws BaseException {
        try {
            validate(recipeRequestInfo);
            if (recipeRequestInfo.getId() != null) {
                Recipe recipe = get(recipeRequestInfo.getId());
                log.debug(String.format("Рецепт найденный по идентификатору = %s", recipe.getId()));
                return RecipeListImpl.builder().recipeList(Lists.newArrayList(recipe)).build();
            } else if (validate(recipeRequestInfo.getName())) {
                String receivedName = recipeRequestInfo.getName();
                log.debug(String.format("Полученное название рецепта %s",receivedName));
                RecipeList recipeList = RecipeMapper.INSTANCE.map(recipeService.findByName(receivedName));
                log.debug(String.format("Рецепты найденные по названию %s = %s",
                        receivedName,recipeList));
                return recipeList;
            }
            return RecipeListImpl.builder().build();
        } catch (Throwable e) {
            log.error(String.format("Ошибка получения рецепта по информации = %s", recipeRequestInfo));
            log.error(e.getMessage(),e.getCause());
            return RecipeListImpl.builder().build();
        }
    }

    /**
     * метод получения списка всех имеющихся рецептов
     * @return список всех найденнх рецептов
     */
    @Override
    public RecipeList getAll(){
        try {
            RecipeList recipeList = RecipeMapper.INSTANCE.map(recipeService.findAll());
            log.debug(String.format("Все имеющиеся рецепты = %s", recipeList));
            return recipeList;
        } catch (Throwable e) {
            log.error("Ошибка получения списка всех рецептов");
            log.error(e.getMessage(), e.getCause());
            return RecipeListImpl.builder().build();
        }
    }

    /**
     * метод получения списка рецептов по списку идентификаторов
     * @param longList список идентификаторов
     * @return список рецептов
     */
    @Override
    public RecipeList getAllById(LongList longList){
        try {
            validate(longList);
            log.debug(String.format(RECEIVED_ID_LIST,longList));
            RecipeList recipeList = RecipeMapper.INSTANCE.map(recipeService.findAll());
            log.debug(String.format("Найденные рецепты = %s", recipeList));
            return recipeList;
        } catch (Throwable e) {
            log.error(String.format("ошибка получения рецептов по списку идентификаторов: %s", longList));
            log.error(e.getMessage(), e.getCause());
            return RecipeListImpl.builder().build();
        }
    }

    /**
     * метод удаления рецепта по идентификатор
     * @param id уникаьлный идентификатор
     * @return логический ответ
     * @throws BaseException
     */
    @Override
    public Boolean deleteById(Long id) throws BaseException {
        try {
            validate(id);
            log.debug(String.format(RECEIVED_ID, id));
            Boolean deleteCheck = recipeService.deleteById(id);
            log.debug(String.format(DELETE_RESULT, deleteCheck));
            return deleteCheck;
        } catch (Throwable e) {
            log.error(String.format("Удаление рецепта по идентификатору = %d, не удалось", id));
            log.error(e.getMessage(),e.getCause());
            return false;
        }
    }

    /**
     * метод удаления рецепта по имени
     * @param recipeRequestInfo класс с информацией для удаления
     * @return логический ответ
     */
    @Override
    public Boolean deleteByName(RecipeRequestInfo recipeRequestInfo) {
        try {
            validate(recipeRequestInfo);
            if (recipeRequestInfo.getId() != null) {
                return deleteById(recipeRequestInfo.getId());
            } else if (validate(recipeRequestInfo.getName())) {
                String receivedName = recipeRequestInfo.getName();
                log.debug(String.format("Полученное название рецепта: %s",receivedName));
                Boolean deleteCheck = recipeService.deleteByName(receivedName);
                log.debug(String.format(DELETE_RESULT,deleteCheck));
                return deleteCheck;
            }
            return false;
        } catch (Throwable e) {
            log.error(String.format("Ошибка удаления рецепта по информации = %s", recipeRequestInfo));
            log.error(e.getMessage(), e.getCause());
            return false;
        }
    }

    /**
     * метод валидации объекта
     * @param o объект для операции валидации
     * @return логичский ответ
     * @throws ValidationException ошибка валидации
     */
    private Boolean validate(Object o) throws ValidationException {
        if (o == null) {
            throw new ValidationException("Некорректный запрос.");
        }
        return true;
    }

}


