package com.simplekitchen.project.dao.entity.city.api;

import com.simplekitchen.project.dao.entity.city.CityNameImpl;

public interface City {

    /**
     * @return id города
     * */
    Long getId();

    /**
     * @return имя города
     * */
    CityNameImpl getCityName();

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
