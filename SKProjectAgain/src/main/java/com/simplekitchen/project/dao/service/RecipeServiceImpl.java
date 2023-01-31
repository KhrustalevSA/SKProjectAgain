package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.recipe.api.Recipe;
import com.simplekitchen.project.dao.repository.RecipeRepository;
import com.simplekitchen.project.dao.service.api.RecipeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * класс сервиса рецептов
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Data
public class RecipeServiceImpl implements RecipeService {

    /**
     * репозиторий рецептов
     */
    private final RecipeRepository recipeRepository;

    /**
     * конструктор сервиса с автоматическим подключением
     * @param recipeRepository
     */
    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * метод сохранения рецепта
     * @param recipe
     * @return сохраненный Optional объект рецепта
     */
    @Override
    public Optional<Recipe> save(Recipe recipe) {
        return Optional.of(recipeRepository.save(recipe));
    }

    /**
     * метод сохранения списка рецептов
     * @param recipeList
     * @return список сохраненных рецептов
     */
    @Override
    public List<Recipe> saveAll(List<Recipe> recipeList) {
        return Lists.newArrayList(recipeRepository.saveAll(recipeList));
    }

    /**
     * метод получения рецпта по уникальному идентификатору
     * @param id
     * @return Optional объект полученного рецепта
     */
    @Override
    public Optional<Recipe> get(Long id) {
        return recipeRepository.findById(id);
    }

    /**
     * метод получения всех рецептов
     * @return список рецептов
     */
    @Override
    public List<Recipe> getAll() {
        return Lists.newArrayList(recipeRepository.findAll());
    }

    /**
     * метод получения рецептов по уникальному идентификатору
     * @param ids
     * @return список рецептов
     */
    @Override
    public List<Recipe> getAllById(List<Long> ids) {
        return Lists.newArrayList(recipeRepository.findAllById(ids));
    }

    /**
     * метод удаления рецепта по его уникальному идентификатору
     * @param id
     * @return Boolean объект
     */
    @Override
    public Boolean deleteById(Long id) {
        recipeRepository.deleteById(id);
        return !recipeRepository.findById(id).isPresent();
    }

    /**
     * метод удаления рецепта по его сущности
     * @param recipe
     * @return Boolean объект
     */
    @Override
    public Boolean delete(Recipe recipe) {
        recipeRepository.delete(recipe);
        return !recipeRepository.findById(recipe.getId()).isPresent();
    }

    /**
     * метод удаления списка рецептов
     * @param recipeList
     * @return Boolean объект
     */
    @Override
    public Boolean deleteAll(List<Recipe> recipeList) {
        recipeRepository.deleteAll(recipeList);
        return Lists.newArrayList(recipeRepository.findAllById(recipeList.stream().map(Recipe::getId).collect(Collectors.toList()))).isEmpty();
    }

    /**
     * метод удаления всех рецептов
     * @return Boolean объект
     */
    @Override
    public Boolean deleteAll() {
        recipeRepository.deleteAll();
        return Lists.newArrayList(recipeRepository.findAll()).isEmpty();
    }
}
