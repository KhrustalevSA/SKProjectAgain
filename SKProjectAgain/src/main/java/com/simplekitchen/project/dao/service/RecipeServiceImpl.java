package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dao.repository.RecipeRepository;
import com.simplekitchen.project.dao.service.api.RecipeService;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;

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
     * @param recipeEntity
     * @return сохраненный Optional объект рецепта
     */
    @Override
    public Optional<RecipeEntity> save(RecipeEntity recipeEntity) {
        return Optional.of(recipeRepository.save(recipeEntity));
    }

    /**
     * метод сохранения списка рецептов
     * @param recipeEntityList
     * @return список сохраненных рецептов
     */
    @Override
    public List<RecipeEntity> saveAll(List<RecipeEntity> recipeEntityList) {
        return Lists.newArrayList(recipeRepository.saveAll(recipeEntityList));
    }

    /**
     * метод получения рецпта по уникальному идентификатору
     * @param id
     * @return Optional объект полученного рецепта
     */
    @Override
    public Optional<RecipeEntity> get(Long id) {
        return recipeRepository.findById(id);
    }

    /**
     * метод получения всех рецептов
     * @return список рецептов
     */
    @Override
    public List<RecipeEntity> getAll() {
        return Lists.newArrayList(recipeRepository.findAll());
    }

    /**
     * метод получения рецептов по уникальному идентификатору
     * @param ids
     * @return список рецептов
     */
    @Override
    public List<RecipeEntity> getAllById(List<Long> ids) {
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
     * @param recipeEntity
     * @return Boolean объект
     */
    @Override
    public Boolean delete(RecipeEntity recipeEntity) {
        recipeRepository.delete(recipeEntity);
        return !recipeRepository.findById(recipeEntity.getId()).isPresent();
    }

    /**
     * метод удаления списка рецептов
     * @param recipeEntityList
     * @return Boolean объект
     */
    @Override
    public Boolean deleteAll(List<RecipeEntity> recipeEntityList) {
        recipeRepository.deleteAll(recipeEntityList);
        return Lists.newArrayList(recipeRepository.findAllById(recipeEntityList.stream().map(RecipeEntity::getId).collect(Collectors.toList()))).isEmpty();
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
