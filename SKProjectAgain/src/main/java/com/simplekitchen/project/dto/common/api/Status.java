package com.simplekitchen.project.dto.common.api;

/**
 * Интерфейс сущности изображений
 * @author KhrustalevSA
 * @since 09.10.2022
 */
public interface Status {

    /**
     * @return статус процесса
     */
    boolean isSuccess();
    /**
     * @return описание статуса
     */
    String getDescription();
}
