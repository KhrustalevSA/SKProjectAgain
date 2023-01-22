package com.simplekitchen.project.dao.entity.user.api;

public interface City {

    /**
     * @return id города
     * */
    Long getUuid();

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
