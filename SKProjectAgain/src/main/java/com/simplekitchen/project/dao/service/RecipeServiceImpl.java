package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.service.api.RecipeService;

public class RecipeServiceImpl extends ServiceBase<RecipeImpl> implements RecipeService {
    public RecipeServiceImpl(){
        super(RecipeImpl.class);
    }
}
