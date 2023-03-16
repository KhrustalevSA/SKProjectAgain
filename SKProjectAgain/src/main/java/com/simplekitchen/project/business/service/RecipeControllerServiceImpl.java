package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.mapper.common.CommonMapper;
import com.simplekitchen.project.business.mapper.recipe.RecipeMapper;
import com.simplekitchen.project.business.service.api.RecipeControllerService;
import com.simplekitchen.project.business.utils.api.ObjectSaveValidator;
import com.simplekitchen.project.business.utils.api.RequestValidator;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dao.service.api.RecipeService;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeResponseInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeListRequestInfo;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeRequestInfo;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeResponseInfo;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    private RecipeService recipeService;

    private RequestValidator<RecipeRequestInfo> requestValidator;

    private ObjectSaveValidator<RecipeListRequestInfo> recipeSaveValidator;

    /**
     * конструктор севриса контроллера рецептов
     * @param recipeServiceDao дао сервис рецептов
     */
    @Autowired
    public RecipeControllerServiceImpl(RecipeService recipeServiceDao,
                                       @Qualifier("recipeInfoRequestValidator") RequestValidator<RecipeRequestInfo> requestValidator,
                                       @Qualifier("recipeSaveValidator") ObjectSaveValidator<RecipeListRequestInfo> recipeSaveValidator) {
        this.recipeService = recipeServiceDao;
        this.requestValidator = requestValidator;
        this.recipeSaveValidator = recipeSaveValidator;
    }

    /**
     * метод сохранения списка рецептов
     * @param recipeListRequestInfo список рецептов для сохранения
     * @return список сохраненных рецептов
     */
    @Override
    public RecipeResponseInfo save(RecipeListRequestInfoImpl recipeListRequestInfo) throws Throwable {
        ObjectUtils.requireNonEmpty(recipeListRequestInfo);
        recipeSaveValidator.validate(recipeListRequestInfo);
        log.debug(String.format("Запрошенный список рецептов = %s.", recipeListRequestInfo));
        List<RecipeEntityImpl> recipeEntityImplList = recipeListRequestInfo.getRecipeList().stream().map(RecipeMapper.INSTANCE::map)
                .collect(Collectors.toList());
        List<RecipeEntity> recipeEntityList = recipeService.save(recipeEntityImplList);
        log.debug(String.format("Сохраненный список рецептов = %s.",recipeEntityList));
            return RecipeResponseInfoImpl.builder()
                    .recipeList(recipeEntityList.stream().map(RecipeMapper.INSTANCE::map).collect(Collectors.toList()))
                    .status(StatusImpl.builder().success(true).build())
                    .build();
    }

    /**
     * метод получения рецептов по имеющейя информации
     * @param recipeRequestInfo запрос с инофрмацией о рецептах
     * @return список найденных рецептов
     * @throws BaseException общий класс ошибок
     */
    @Override
    public List<Recipe> get(RecipeRequestInfoImpl recipeRequestInfo) throws BaseException {
        requestValidator.validate(recipeRequestInfo);
        if (recipeRequestInfo.getId() != null) {
            Recipe foundRecipe = getById(recipeRequestInfo.getId());
            return foundRecipe != null ? (Collections.singletonList(foundRecipe)) : Collections.emptyList();
        } else if (StringUtils.isNotBlank(recipeRequestInfo.getName())) {
            return getByName(recipeRequestInfo.getName());
        }
        return Collections.emptyList();
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
    public List<Recipe> getAllById(LongListImpl longList){
        try {
            validate(longList);
            log.debug(String.format(RECEIVED_ID_LIST,longList));
            List<RecipeEntity> recipeList = recipeService.findAllById(CommonMapper.INSTANCE.map(longList));
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


