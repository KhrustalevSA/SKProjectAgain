package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.common.entity.LongListImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeImplListImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeListImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeList;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.repository.RecipeRepository;
import com.simplekitchen.project.dao.service.api.RecipeService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * класс сервиса рецептов
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Slf4j
@Service
@Data
public class RecipeServiceImpl implements RecipeService {

    private static final String RECEIVED_RECIPE_LIST = "Полученный список рецептов: %s";
    private static final String RECEIVED_ID = "Полученный уникальный идентификатор: %d";
    private static final String RECEIVED_RECIPE_ID_LIST = "Полученный список идентификаторов %s";

    /**
     * репозиторий рецептов
     */
    private final RecipeRepository recipeRepository;

    /**
     * конструктор сервиса с автоматическим подключением
     * @param recipeRepository репозиторий рецептов
     */
    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * метод сохранения рецепта
     * @param recipeEntity сущность рецепта
     * @return сохраненный рецепт
     */
    @Override
    public RecipeEntity save(RecipeEntityImpl recipeEntity) throws DataBaseException {
        try {
            log.debug(String.format("Рецепт пришедший на сохранение %s.",recipeEntity));
            RecipeEntity savedRecipe = recipeRepository.save(recipeEntity);
            log.debug(String.format("Сохраненный рецепт: %s.",savedRecipe));
            return savedRecipe;
        } catch (Exception e) {
            log.error(String.format("Не удалось сохранить рецепт: %s", recipeEntity));
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * метод сохранения списка рецептов
     * @param recipeList список рецептов
     * @return список сохраненных рецептов
     */
    @Override
    public RecipeList saveAll(RecipeImplListImpl recipeList) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_RECIPE_LIST, recipeList));
            Iterable<RecipeEntityImpl> recipeEntitiesI = recipeRepository.saveAll(recipeList.getRecipeList());
            List<RecipeEntityImpl> receivedRecipeList = (List<RecipeEntityImpl>) recipeEntitiesI;
            log.debug(String.format("Cохраненный список рецептов: %s", receivedRecipeList));
            return RecipeListImpl.builder().recipeEntityList(receivedRecipeList).build();
        } catch (Exception e) {
            log.error(String.format("Не удалось сохранить список рецептов: %s",recipeList));
            throw new DataBaseException(e.getMessage(), e.getCause());
        }
    }

    /**
     * метод получения рецпта по уникальному идентификатору
     * @param id идентификатор рецепта
     * @return найденный рецепт
     */
    @Override
    public RecipeEntity findById(Long id) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_ID,id));
            RecipeEntity recipeEntity = recipeRepository.findById(id).orElse(null);
            log.debug(String.format("Найденный рецепт %s", recipeEntity));
            return recipeEntity;
        } catch (Exception e) {
            log.error(String.format("Не удалось найти рецепт по идентификатору %d", id));
            throw new DataBaseException(e.getMessage(), e.getCause());
        }
    }

    /**
     * метод поиска рецепта по названию рецепта
     * @param name название рецепта
     * @return список рецептов
     * @throws DataBaseException ошибки базы данных
     */
    @Override
    public RecipeList findByName(String name) throws DataBaseException {
        try {
            log.debug(String.format("Полученное название рецепта %s", name));
            List<RecipeEntityImpl> recipeEntityList = recipeRepository.findByName(name).orElse(null);
            log.debug(String.format("Найденные рецепты %s", recipeEntityList));
            return RecipeListImpl.builder()
                    .recipeEntityList(recipeEntityList)
                    .build();
        } catch (Exception e) {
            log.error(String.format("Не удалось найти рецепты с названием: %s", name));
            throw new DataBaseException(e.getMessage(), e.getCause());
        }
    }

    /**
     * метод поиска рецепта по сложности
     * @param difficulty сложность рецепта
     * @return список рецептов
     * @throws DataBaseException ошибки базы данных
     */
    @Override
    public RecipeList findByDifficulty(String difficulty) throws DataBaseException {
        try {
            log.debug(String.format("Полученная сложность %s", difficulty));
            List<RecipeEntityImpl> recipeEntityList = recipeRepository.findByDifficulty(difficulty).orElse(null);
            log.debug(String.format("По сложности %s нашлись рецпты: %s", difficulty,recipeEntityList));
            return RecipeListImpl.builder()
                    .recipeEntityList(recipeEntityList)
                    .build();
        } catch (Exception e) {
            log.error(String.format("Не удалось найти рецепты со сложностью: %s", difficulty));
            throw new DataBaseException(e.getMessage(), e.getCause());
        }
    }

    /**
     * метод поиска рецептов по времени приготовления
     * @param cookingTime время приготовления рецепта
     * @return список рецептов
     * @throws DataBaseException ошибки базы данных
     */
    @Override
    public RecipeList findByCookingTime(Long cookingTime) throws DataBaseException {
        try {
            log.debug(String.format("Полученное время готовки %d", cookingTime));
            List<RecipeEntityImpl> recipeEntityList = recipeRepository.findByCookingTime(cookingTime).orElse(null);
            log.debug(String.format("Найденный по времени %d рецепты %s",cookingTime,recipeEntityList));
            return RecipeListImpl.builder().recipeEntityList(recipeEntityList).build();
        } catch (Exception e) {
            log.error(String.format("Не удалось найти рецепты с временем готовки = %d",cookingTime));
            throw new DataBaseException(e.getMessage(), e.getCause());
        }
    }

    /**
     * метод получения всех рецептов
     * @return список рецептов
     */
    @Override
    public RecipeList findAll() throws DataBaseException {
        try {
            List<RecipeEntityImpl> recipeEntityList = (List<RecipeEntityImpl>) recipeRepository.findAll();
            log.debug(String.format(RECEIVED_RECIPE_LIST, recipeEntityList));
            return RecipeListImpl.builder().recipeEntityList(recipeEntityList).build();
        } catch (Exception e) {
            log.error("Не удалось найти все рецепты");
            throw new DataBaseException(e.getMessage(),e.getCause());
        }
    }

    /**
     * метод получения рецептов по списку уникальных идентификаторов
     * @param longList список идентификаторов
     * @return список рецептов
     */
    @Override
    public RecipeList findAllById(LongListImpl longList) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_RECIPE_ID_LIST,longList));
            List<RecipeEntityImpl> recipeEntityList = (List<RecipeEntityImpl>) recipeRepository.findAllById(longList.getLongList());
            log.debug(String.format(RECEIVED_RECIPE_LIST, recipeEntityList));
            return RecipeListImpl.builder().recipeEntityList(recipeEntityList).build();
        } catch (Exception e) {
            log.error(String.format("Не удалось найти рецепты по идентификаторам: %s",longList));
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * метод удаления рецепта по его уникальному идентификатору
     * @param id иеднтификатор рецепта
     * @return логичский ответ
     */
    @Override
    public Boolean deleteById(Long id) throws DataBaseException {
        try {
            log.debug(String.format(RECEIVED_ID,id));
            Optional<RecipeEntityImpl> recipeEntityFoundById = recipeRepository.findById(id);
            log.debug(String.format("Удаляемый рецепт %s",recipeEntityFoundById));
            recipeRepository.deleteById(id);
            recipeEntityFoundById = recipeRepository.findById(id);
            if (recipeEntityFoundById.isPresent()) {
                log.debug(String.format("Удаление рецепта по идентификатору %d не удалось", id));
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error(String.format("Не удалось удалить рецепт по идентификатору %d", id));
            throw new DataBaseException(e.getMessage(), e.getCause());
        }

    }

    /**
     * метод удаления рецептов по названию
     * @param name название рецептов
     * @return логический ответ
     * @throws DataBaseException ошибки базы данных
     */
    @Override
    public Boolean deleteByName(String name) throws DataBaseException {
        try {
            log.debug(String.format("Полученное название рецепта %s", name));
            List<RecipeEntityImpl> recipeEntityList = recipeRepository.findByName(name).orElse(null);
            if (recipeEntityList == null) {
                log.error(String.format("рецепт по имени %s не найден", name));
                return false;
            }
            log.debug(String.format("Удаляемый список рецептов %s", recipeEntityList));
            recipeRepository.deleteAllByName(name);
            Optional<List<RecipeEntityImpl>> deleteRecipeEntities = recipeRepository.findByName(name);
            if (deleteRecipeEntities.isPresent()) {
                log.debug(String.format("Удаление рецептов по имени %s не удалось", name));
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error(String.format("Не удалось удалить рецепт по имени %s", name));
            throw new DataBaseException(e.getMessage(), e.getCause());
        }
    }

    /**
     * метод удаления списка рецептов по списку уникальных идентификаторов
     * @param longList список идентификаторов
     * @return логический ответ
     */
    @Override
    public Boolean deleteAllById(LongListImpl longList) throws DataBaseException {
        String errorMessage = String.format("Ошибка удаления рецептов по списку идентификаторов %s", longList);
        try {
            log.debug(String.format(RECEIVED_RECIPE_ID_LIST,longList));
            List<RecipeEntityImpl> recipeEntityList = (List<RecipeEntityImpl>) recipeRepository.findAllById(longList.getLongList());
            log.debug(String.format(RECEIVED_RECIPE_LIST, recipeEntityList));
            recipeRepository.deleteAllById(longList.getLongList());

            recipeEntityList = (List<RecipeEntityImpl>) recipeRepository.findAllById(longList.getLongList());
            if (!recipeEntityList.isEmpty()) {
                log.debug(errorMessage);
                return false;
            }
            return false;
        } catch (Exception e) {
            log.debug(errorMessage);
            throw new DataBaseException(e.getMessage(), e.getCause());
        }
    }

}