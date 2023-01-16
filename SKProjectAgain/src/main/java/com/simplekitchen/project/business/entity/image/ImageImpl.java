package com.simplekitchen.project.business.entity.image;

import com.simplekitchen.project.business.entity.image.api.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Класс реализующий интерфейс Image
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
    private String uuid;

    /**
     * поле для хранения пути к картинке
     */
    private String path;

    /**
     * поле для хранения url к картинке
     */
    private String url;
}
