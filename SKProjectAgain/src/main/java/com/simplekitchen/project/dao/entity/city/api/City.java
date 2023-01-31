package com.simplekitchen.project.dao.entity.city.api;

import com.simplekitchen.project.dao.entity.city.CityNameImpl;

/**
 * интерфейс ДАО сущности города
 * @author KhrustalevSA
 * @since 31.01.2023
 */
public interface City {

    /**
     * @return id города
     * */
    Long getId();

    /**
     * @return название города
     * */
    CityNameImpl getCityName();

    /**
     * @return название области
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
