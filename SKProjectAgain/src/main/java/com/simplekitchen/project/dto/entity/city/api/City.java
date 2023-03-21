package com.simplekitchen.project.dto.entity.city.api;

import com.simplekitchen.project.dto.entity.user.UserImpl;

/**
 * интерфейс описывающий ДТО класс города
 * @author KhrustalevSA
 * @since 31.01.2023
 */
public interface City {
    /**
     * @return id города
     * */
    Long getId();

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

    UserImpl getUser();
}
