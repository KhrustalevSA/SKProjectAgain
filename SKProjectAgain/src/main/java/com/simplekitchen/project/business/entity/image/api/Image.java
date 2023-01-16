package com.simplekitchen.project.business.entity.image.api;

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
}
