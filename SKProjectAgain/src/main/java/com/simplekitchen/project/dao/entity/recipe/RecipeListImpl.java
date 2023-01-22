package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.recipe.api.Recipe;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeList;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

public class RecipeListImpl implements RecipeList {

    private List<Recipe> recipeList;

    public RecipeListImpl() {
    }

    public RecipeListImpl(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeListImpl)) return false;
        RecipeListImpl that = (RecipeListImpl) o;
        return Objects.equals(getRecipeList(), that.getRecipeList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecipeList());
    }

    @Override
    public String toString() {
        return "RecipeListImpl{" +
                "recipeList=" + recipeList +
                '}';
    }
}
