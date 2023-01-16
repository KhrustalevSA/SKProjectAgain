package com.simplekitchen.project.dto.entity.user;


import lombok.*;

/**
 * Класс реализовывающий интерфейс City
 * @see com.simplekitchen.project.dto.entity.user.api.City
 * @Author KhrustalevSA
 * @since 28.09.2022
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityImpl implements com.simplekitchen.project.dto.entity.user.api.City {

    /**
     * поле уникального идентификатора города пользователя
     */
    private Long uuid;

    /**
     * поле имени города
     * */
    private String cityName;

    /**
     * поле имени области
     * */
    private String regionName;

    /**
     * поле названия улицы
     * */
    private String streetName;

    /**
     * поле номера дома
     * */
    private Long houseNumber;

    /**
     * поле номера подъезда
     * */
    private Long entranceNumber;

    /**
     * поле номера квартиры
     * */
    private Long flatNumber;
}
