package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.recipe.IngredientImpl;
import com.simplekitchen.project.dao.service.api.IngredientService;

public class IngredientServiceImpl extends ServiceBase<IngredientImpl> implements IngredientService {
    public IngredientServiceImpl(){
        super(IngredientImpl.class);
    }
}
