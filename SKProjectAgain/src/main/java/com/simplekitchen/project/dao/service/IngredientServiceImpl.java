package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.Ingredient.IngredientImpl;
import com.simplekitchen.project.dao.repository.IngredientRepository;
import com.simplekitchen.project.dao.service.api.IngredientService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Optional<IngredientImpl> save(IngredientImpl ingredient) {
        return Optional.of(ingredientRepository.save(ingredient));
    }

    @Override
    public List<IngredientImpl> saveAll(List<IngredientImpl> ingredientList) {
        return Lists.newArrayList(ingredientRepository.saveAll(ingredientList));
    }

    @Override
    public Optional<IngredientImpl> get(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public List<IngredientImpl> getAll() {
        return Lists.newArrayList(ingredientRepository.findAll());
    }

    @Override
    public List<IngredientImpl> getAllById(List<Long> ids) {
        return Lists.newArrayList(ingredientRepository.findAllById(ids));
    }

    @Override
    public Boolean deleteById(Long id) {
        ingredientRepository.deleteById(id);
        return !ingredientRepository.findById(id).isPresent();
    }

    @Override
    public Boolean delete(IngredientImpl ingredient) {
        ingredientRepository.delete(ingredient);
        return !ingredientRepository.findById(ingredient.getId()).isPresent();
    }

    @Override
    public Boolean deleteAll(List<IngredientImpl> ingredientList) {
        ingredientRepository.deleteAll(ingredientList);
        return Lists.newArrayList(ingredientRepository.findAllById(ingredientList.stream().map(IngredientImpl::getId).collect(Collectors.toList()))).isEmpty();
    }

    @Override
    public Boolean deleteAll() {
        ingredientRepository.deleteAll();
        return Lists.newArrayList(ingredientRepository.findAll()).isEmpty();
    }

}
