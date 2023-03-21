package com.simplekitchen.project.dto.entity.image;

import com.simplekitchen.project.dto.entity.image.api.Image;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс реализующий интерфейс ImageEntity
 * @see Image
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageImpl implements Image {
    /**
     * поле уникального идентификатора изображения
     */
    private String id;

    /**
     * поле для хранения пути к картинке
     */
    private String path;

    /**
     * поле для хранения url к картинке
     */
    private String url;


}
