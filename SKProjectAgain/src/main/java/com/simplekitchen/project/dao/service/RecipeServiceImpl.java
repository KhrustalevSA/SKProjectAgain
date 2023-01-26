package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.recipe.api.Recipe;
import com.simplekitchen.project.dao.repository.RecipeRepository;
import com.simplekitchen.project.dao.service.api.RecipeService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Optional<Recipe> save(Recipe recipe) {
        return Optional.of(recipeRepository.save(recipe));
    }

    @Override
    public List<Recipe> saveAll(List<Recipe> recipeList) {
        return Lists.newArrayList(recipeRepository.saveAll(recipeList));
    }

    @Override
    public Optional<Recipe> get(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public List<Recipe> getAll() {
        return Lists.newArrayList(recipeRepository.findAll());
    }

    @Override
    public List<Recipe> getAllById(List<Long> ids) {
        return Lists.newArrayList(recipeRepository.findAllById(ids));
    }

    @Override
    public Boolean deleteById(Long id) {
        recipeRepository.deleteById(id);
        return !recipeRepository.findById(id).isPresent();
    }

    @Override
    public Boolean delete(Recipe recipe) {
        recipeRepository.delete(recipe);
        return !recipeRepository.findById(recipe.getId()).isPresent();
    }

    @Override
    public Boolean deleteAll(List<Recipe> recipes) {
        recipeRepository.deleteAll(recipes);
        return Lists.newArrayList(recipeRepository.findAllById(recipes.stream().map(Recipe::getId).collect(Collectors.toList()))).isEmpty();
    }

    @Override
    public Boolean deleteAll() {
        recipeRepository.deleteAll();
        return Lists.newArrayList(recipeRepository.findAll()).isEmpty();
    }
}
