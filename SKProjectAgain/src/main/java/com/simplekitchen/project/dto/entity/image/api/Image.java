package com.simplekitchen.project.dto.entity.image.api;

import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;

import java.util.List;

/**
 * Интерфейс dto изображений
 * @author KhrustalevSA
 * @since 03.10.2022
 */
public interface Image {
    /**
     * @return уникальный идентификатор изображения
     */
    String getUuid();

    /**
     * @return путь к изображению
     */
    String getPath();

    /**
     * @return url изображения
     */
    String getUrl();

    /**
     * @return Список рецптов
     */
    List<RecipeImpl> getRecipe();}
