package com.simplekitchen.project.business.mapper.recipe;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImplListImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * интерфейс маппера для объектов рецепта
 * @since 21.02.2023
 * @author KhrustalevSA
 */
@Mapper
public interface RecipeMapper {

    /**
     * переменная - экземпляр класса
     */
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    /**
     * метод преобразования ДТО рецепта в сущность ДАО рецепта
     * @param daoRecipe ДАО сущность рецепта
     * @return ДТО объект рецепта
     */
    RecipeImpl map(RecipeEntity daoRecipe);

    /**
     * метод преобраования ДАО сущность в ДТО объект
     * @param dtoRecipe ДТО объект рецепта
     * @return ДАО сущность рецепта
     */
    RecipeEntityImpl map(Recipe dtoRecipe);

    /**
     * метод преобразования класса списка рецептов к интерфейсу списка
     * @param recipeList интерфейс списка рецептов
     * @return Объект списка рецептов
     */
    RecipeListImpl map(RecipeList recipeList);

    /**
     * метод преобразования ДАО списка рецептов к ДТО объекту списку рецептов
     * @param recipeList ДАО список рецептов
     * @return ДТО список рецептов
     */
    @Mapping(target = "recipeList", source = "recipeEntityList")
    RecipeListImpl map(com.simplekitchen.project.dao.entity.recipe.api.RecipeList recipeList);

    /**
     * метод преобразования ДТО списка рецептов к ДАО объекту списку рецептов
     * @param recipeImplList ДТО объект списка рецептов
     * @return ДАО список рецептов
     */
    com.simplekitchen.project.dao.entity.recipe.RecipeImplListImpl map(RecipeImplListImpl recipeImplList);

}
