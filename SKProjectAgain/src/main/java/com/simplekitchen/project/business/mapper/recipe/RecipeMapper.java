package com.simplekitchen.project.business.mapper.recipe;

import com.simplekitchen.project.dao.entity.city.CityNameEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dto.entity.city.CityImpl;
import com.simplekitchen.project.dto.entity.city.api.City;
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
     * метод преобразования интерфейса списка рецептов к классу списка
     * @param recipeList интерфейс списка рецептов
     * @return Объект списка рецептов
     */
    RecipeListImpl map(RecipeList recipeList);

    /**
     * метод прелобразования ДАО класса с именем города к ДТО классу города
     * @param cityName класс с именем города
     * @return ДТО класс города
     */
    @Mapping(target = "cityName", source = "cityName.cityName")
    CityImpl map(CityNameEntityImpl cityName);

    default String mapCityName(CityNameEntityImpl cityName) {
        return cityName.getCityName();
    }

    default CityNameEntityImpl mapToCityName(String cityName) {
        return CityNameEntityImpl.builder().cityName("name").build();
    }
}
