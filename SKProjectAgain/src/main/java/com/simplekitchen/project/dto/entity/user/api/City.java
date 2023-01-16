package com.simplekitchen.project.dto.entity.user.api;

/**
 * Интерфейс объекта места жительства пользователя
 * @Author Khrustalev-sa
 * @since 28.09.2022
 * */
public interface City {

    /**
     * @return имя города
     * */
    String getCityName();

    /**
     * @return имя области
     * */
    String getRegionName();

    /**
     * @return название улицы
     * */
    String getStreetName();

    /**
     * @return номер дома
     * */
    Long getHouseNumber();

    /**
     * @return номер подъезда
     * */
    Long getEntranceNumber();

    /**
     * @return номер квартиры
     * */
    Long getFlatNumber();

}
