package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.entity.common.api.LongList;
import com.simplekitchen.project.business.entity.recipe.RecipeRequestInfoImpl;
import com.simplekitchen.project.business.entity.recipe.api.RecipeRequestInfo;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.mapper.recipe.RecipeMapper;
import com.simplekitchen.project.business.service.api.RecipeControllerService;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.RecipeService;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
     * @throws BaseException общий класс ошибок сервиса
     */
    @Override
    public Recipe save(RecipeImpl recipe) throws BaseException {
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
     */
    @Override
    public List<Recipe> saveAll(List<RecipeImpl> recipeList) {
        try {
            validate(recipeList);
            log.debug(String.format("Запрошенный список рецептов = %s.", recipeList));
            List<RecipeEntity> recipeEntities = recipeService.saveAll(
                    recipeList.stream().map(RecipeMapper.INSTANCE::map).collect(Collectors.toList()));
            log.debug(String.format("Сохраненный список рецептов = %s.",recipeEntities));
            return recipeEntities.stream().map(RecipeMapper.INSTANCE::map).collect(Collectors.toList());
        } catch (Throwable e) {
            log.error(String.format("Не удалось сохранить список рецептов %s.", recipeList));
            log.error(e.getMessage(),e.getCause());
            return null;
        }
    }

    /**
     * метод получения рецептов по имеющейя информации
     * @param recipeRequestInfo запрос с инофрмацией о рецептах
     * @return список найденных рецептов
     * @throws BaseException общий класс ошибок
     */
    @Override
    public List<Recipe> get(RecipeRequestInfoImpl recipeRequestInfo) throws BaseException {
        ObjectUtils.requireNonEmpty(recipeRequestInfo);
        if (recipeRequestInfo.getId() != null) {
            return Lists.newArrayList(getById(recipeRequestInfo.getId()));
        } else if (StringUtils.isNotBlank(recipeRequestInfo.getName())) {
            return getByName(recipeRequestInfo.getName());
        }
        return null;
    }

    /**
     * метод поиска рецептов по идентификатору
     * @param id уникальный идентификатор рецепта
     * @return найденный рецепт
     */
    protected Recipe getById(@NonNull Long id) {
        try {
            ObjectUtils.requireNonEmpty(id);
            log.debug(String.format(RECEIVED_ID,id));
            RecipeEntity recipeEntity = recipeService.findById(id);
            log.debug(String.format("Рецепт найденный по идентификатору %s = %s", id, recipeEntity));
            return RecipeMapper.INSTANCE.map(recipeEntity);
        }  catch (Throwable e) {
            log.error(String.format("Ошибка нахождения рецепта по идентификатору %s", id));
            log.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    /**
     * мето поиска рецептов по названию
     * @param name название рецепта
     * @return список найденных рецептов
     */
    protected List<Recipe> getByName(@NonNull String name) {
        try {
            ObjectUtils.requireNonEmpty(name);
            log.debug(String.format("Запрошенное имя %s", name));
            List<RecipeEntity> recipeEntities = recipeService.findByName(name);
            log.debug(String.format("Список найденных рецептов: %s", recipeEntities));
            return recipeEntities.stream().map(RecipeMapper.INSTANCE::map).collect(Collectors.toList());
        } catch (Throwable e) {
            log.error(String.format("Ошибка нахождения рецепта по названию %s", name));
            log.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    /**
     * метод получения списка всех имеющихся рецептов
     * @return список всех найденнх рецептов
     */
    @Override
    public List<Recipe> getAll(){
        try {
            List<Recipe> recipeList =
                    recipeService.findAll().stream().map(RecipeMapper.INSTANCE::map).collect(Collectors.toList());
            log.debug(String.format("Все имеющиеся рецепты = %s", recipeList));
            return recipeList;
        } catch (Throwable e) {
            log.error("Ошибка получения списка всех рецептов");
            log.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    /**
     * метод получения списка рецептов по списку идентификаторов
     * @param longList список идентификаторов
     * @return список рецептов
     */
    @Override
    public List<Recipe> getAllById(LongList longList){
        try {
            validate(longList);
            log.debug(String.format(RECEIVED_ID_LIST,longList));
            List<RecipeEntity> recipeList = recipeService.findAll();
            log.debug(String.format("Найденные рецепты = %s", recipeList));
            return recipeList.stream().map(RecipeMapper.INSTANCE::map).collect(Collectors.toList());
        } catch (Throwable e) {
            log.error(String.format("ошибка получения рецептов по списку идентификаторов: %s", longList));
            log.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    /**
     * метод удаления рецепта по идентификатор
     * @param id уникаьлный идентификатор
     * @return логический ответ
     * @throws BaseException общий класс ошибки
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
                log.debug(String.format("Полученное название рецепта: %s", receivedName));
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


