package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.ingredient.IngredientEntityImpl;
import com.simplekitchen.project.dao.repository.IngredientRepository;
import com.simplekitchen.project.dao.service.api.IngredientService;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * класс сервиса ингредиентов
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Service
@Data
public class IngredientServiceImpl implements IngredientService {

    /**
     * репозиторий ингредиентов
     */
    private final IngredientRepository ingredientRepository;

    /**
     * конструктор сервиса с автоматическим подключением
     * @param ingredientRepository
     */
    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * метод сохранения ингредиента
     * @param ingredient
     * @return сохраненный Optional объект ингредиента
     */
    @Override
    public Optional<IngredientEntityImpl> save(IngredientEntityImpl ingredient) {
        return Optional.of(ingredientRepository.save(ingredient));
    }

    /**
     * метод сохранения списка ингредиентов
     * @param ingredientList
     * @return список сохраненных ингредиентов
     */
    @Override
    public List<IngredientEntityImpl> saveAll(List<IngredientEntityImpl> ingredientList) {
        return Lists.newArrayList(ingredientRepository.saveAll(ingredientList));
    }

    /**
     * метод получения ингредиента по уникальному идентификатору
     * @param id
     * @return Optional объект полученного ингредиента
     */
    @Override
    public Optional<IngredientEntityImpl> get(Long id) {
        return ingredientRepository.findById(id);
    }

    /**
     * метод получения всех ингредиентов
     * @return список ингредиентов
     */
    @Override
    public List<IngredientEntityImpl> getAll() {
        return Lists.newArrayList(ingredientRepository.findAll());
    }

    /**
     * метод получения ингредиентов по уникальному идентификатору
     * @param ids
     * @return список ингредиентов
     */
    @Override
    public List<IngredientEntityImpl> getAllById(List<Long> ids) {
        return Lists.newArrayList(ingredientRepository.findAllById(ids));
    }

    /**
     * метод удаления ингредиента по его уникальному идентификатору
     * @param id
     * @return Boolean объект
     */
    @Override
    public Boolean deleteById(Long id) {
        ingredientRepository.deleteById(id);
        return !ingredientRepository.findById(id).isPresent();
    }

    /**
     * метод удаления ингредиента по его сущности
     * @param ingredient
     * @return Boolean объект
     */
    @Override
    public Boolean delete(IngredientEntityImpl ingredient) {
        ingredientRepository.delete(ingredient);
        return !ingredientRepository.findById(ingredient.getId()).isPresent();
    }

    /**
     * метод удаления списка ингредиентов
     * @param ingredientList
     * @return Boolean объект
     */
    @Override
    public Boolean deleteAll(List<IngredientEntityImpl> ingredientList) {
        ingredientRepository.deleteAll(ingredientList);
        return Lists.newArrayList(ingredientRepository.findAllById(ingredientList.stream().map(IngredientEntityImpl::getId).collect(Collectors.toList()))).isEmpty();
    }

    /**
     * метод удаления всех ингредиентов
     * @return Boolean объект
     */
    @Override
    public Boolean deleteAll() {
        ingredientRepository.deleteAll();
        return Lists.newArrayList(ingredientRepository.findAll()).isEmpty();
    }

}
