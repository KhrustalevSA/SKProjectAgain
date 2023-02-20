package com.simplekitchen.project.business.mapper.recipe;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeImpl map(RecipeEntity daoRecipe);

    RecipeEntityImpl map(Recipe dtoRecipe);

    @Mapping(target = "recipeList", source = "recipeEntityList")
    RecipeListImpl map(com.simplekitchen.project.dao.entity.recipe.api.RecipeList recipeList);

    com.simplekitchen.project.dao.entity.recipe.RecipeListImpl map(RecipeList recipeList);

}
