package com.simplekitchen.project.dto.entity.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс реализующий интерфейс Image
 * @see com.simplekitchen.project.dto.entity.image.api.Image
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageImpl implements com.simplekitchen.project.dto.entity.image.api.Image {
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

    public String getUuid() {
        return uuid;
    }

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }
}
